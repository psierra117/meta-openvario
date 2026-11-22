# Copyright (C) 2014 Unknow User <unknow@user.org>
# Released under the MIT license (see COPYING.MIT for the terms)

PR = "r0"
RCONFLICTS:${PN} = "opensoar-testing"

require openvario.inc

# OpenSoar 7.46.26.1 (2026-11-06), based on XCSoar 7.46.1.
SRC_URI = "git://github.com/OpenSoaring/OpenSoar.git;protocol=https;nobranch=1;tag=v${PV}"
SRCREV = "41342dbabfa89a22b04af3a9ba4c8d9dbe9d52f5"

# This release's bundled Boost version (build/libboost.mk).
BOOST_VERSION = "1.92.0"
BOOST_SHA256HASH = "5c1d40cb8e19adbf740a4ec2da35b3e58f3f5804b1dce44deb53df72193cbc6c"

# The touch-screen default for OpenVario is built in since 7.46 (patch 0007
# no longer needed). Use the system zxing-cpp like XCSoar 7.46 and rotate
# relative mouse motion correctly with XCSOAR_KMS_INPUT_TRANSFORM=xcsoar.
OPENSOAR_PATCHES = " \
	file://0001-zxing-use-system-library-on-linux.patch \
	file://0002-libinput-rotate-relative-mouse-motion-into-physical-.patch \
"
# OpenSoar 7.46 maps libinput key codes to characters with xkbcommon;
# the keyboard layouts themselves come from xkeyboard-config at runtime.
DEPENDS:append = " zxing-cpp libxkbcommon"
RRECOMMENDS:${PN} += "xkeyboard-config"

# Build for the OpenVario device without the CUBIE2 target's
# "-isystem /usr/include", which would point the cross compiler at the
# build host's headers. Command-line variables override targets.mk.
OPENSOAR_TARGET = "TARGET=UNIX TARGET_IS_OPENVARIO=y TARGET_IS_OVDEVICE=y"

require opensoar.inc
