# Copyright (C) 2014 Unknow User <unknow@user.org>
# Released under the MIT license (see COPYING.MIT for the terms)

PR = "r2"

S = "${WORKDIR}/git"

inherit systemd

SRC_URI = "git://github.com/Openvario/sensord.git;protocol=https;branch=master"
SRCREV = "0894d5f166ef7b015bcf35296291596002bd4618"

require sensord.inc
