#!/bin/bash

HOME=/home/root

if [ -e "$HOME/recover_data/upgrade.cfg" ]; then
    echo "'upgrade.cfg' exist!!"
    source $HOME/recover_data/upgrade.cfg
    
    # ==================== Brightness ========================
    if [ ! "$BRIGHTNESS" = "" ]; then 
      echo "$BRIGHTNESS" > /sys/class/backlight/lcd/brightness
      echo " [####======] Brightness set to '$BRIGHTNESS'"
    fi
    # =========== Daemons variod, sensord and dropbear.socket(ssh) ====
    # Restore variod, sensord and ssh status 
    for daemon in sensord variod dropbear.socket; do
      case $daemon in 
        dropbear.socket) d_state="$ssh_d";;
        sensord)         d_state="$sensord";;
        variod)          d_state="$variod";;
      esac
      case $d_state in
        enabled)  /bin/systemctl  enable --quiet --now $daemon
                  /bin/systemctl   start --quiet --now $daemon
                  echo " [#####=====] $daemon has been enabled."
                  ;;
        disabled) /bin/systemctl disable --quiet --now $daemon
                  echo " [#####=====] $daemon has been disabled."
                  ;;
      esac
    done
    # ==================== Recover Data ======================
    if [ "$UPGRADE_TYPE" == "2" ]; then 
      if [ ! -d $DATADIR/OpenSoarData ]; then
        # the data dir is new and has to be filled from USB, this stick should be still 
        # available after upgrade
        # This is only the case on an upgrade from old to a new system
        mkdir -p $DATADIR/OpenSoarData
        echo "'data/OpenSoarData'is new and has to be filled..."  >> $DEBUG_LOG
      fi
      if [ ! -d $DATADIR/XCSoarData ]; then
        mkdir -p $DATADIR/XCSoarData
        echo "'data/XCSoarData'is new and has to be filled..."  >> $DEBUG_LOG
      fi
      file_count=$(find $HOME/.xcsoar -maxdepth 1 -type f | wc -l)
      if (( $file_count > 6 )); then
        /usr/bin/psplash-message "Restoring flight data..." 2>/dev/null || true
        # Remove the restored copy only after both transfers succeeded.
        # Otherwise ~/.xcsoar keeps the image defaults, which ovmenu-ng
        # seeds into the data partition without overwriting user files.
        if rsync -ruvtcE --progress $HOME/.xcsoar/* $DATADIR/OpenSoarData/ \
                    --delete --exclude cache  --exclude logs &&
           rsync -ruvtcE --progress $HOME/.xcsoar/* $DATADIR/XCSoarData/ \
                      --delete --exclude cache  --exclude logs; then
          sync
          echo "rsync from .xcsoar to $DATADIR/OpenSoarData and XCSoarData"  >> $DEBUG_LOG
          rm -rvf $HOME/.xcsoar/*
        else
          echo "restore from .xcsoar failed; keeping $HOME/.xcsoar"  >> $DEBUG_LOG
        fi
      fi
      if [ -d $HOME/.glider_club ]; then
        /usr/bin/psplash-message "Restoring flight data..." 2>/dev/null || true
        mkdir -p $DATADIR/.glider_club
        rsync -ruvtcE --progress $HOME/.glider_club/* $DATADIR/.glider_club/
        sync
        rm -rvf $HOME/.glider_club
      fi
    fi
    # ==================== End ===============================
    mv $HOME/recover_data/upgrade.cfg $HOME/recover_data/_upgrade.cfg
fi

