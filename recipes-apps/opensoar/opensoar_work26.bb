# Copyright (C) 2014 Unknow User <unknow@user.org>
# Released under the MIT license (see COPYING.MIT for the terms)

PR = "r0"
RCONFLICTS:${PN} = "opensoar-testing"

require openvario.inc

SRC_URI = "git://github.com/OpenSoaring/OpenSoar.git;protocol=https;branch=work26"
SRCREV = "e9a99b11d50c4452cdb1ca7fcc78a7d72d61055b"

# This release's bundled Boost version (gleich wie XCSoar 7.46).
BOOST_VERSION = "1.90.0"
BOOST_SHA256HASH = "49551aff3b22cbc5c5a9ed3dbc92f0e23ea50a0f7325b0d198b705e8ee3fc305"

require opensoar.inc
