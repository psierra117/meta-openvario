# meta-sunxi adds the "kmsro" Gallium driver for mainline sunxi graphics
# (its mesa.bbappend is masked in ovlinux.conf). Mesa 26 no longer has a
# separate kmsro driver: render-only support is part of the lima driver.
PACKAGECONFIG:append:use-mailine-graphics = " lima"
