DESCRIPTION = "Linux kernel for OpenVario (6.18 LTS)"
SECTION = "kernel"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

PR = "1"

inherit kernel kernel-yocto siteinfo


KBRANCH = "linux-6.18.y"

# KERNEL_CFG=no-debug.cfg
KERNEL_CFG = "debug.cfg"

# v6.18.64
SRCREV = "fd7cfd39c88596fec816639ad83cbf3e9d316372"

SRC_URI = " \
	git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;branch=${KBRANCH};protocol=https \
	\
	file://defconfig \
	\
	file://systemd.cfg \
	file://${KERNEL_CFG} \
	file://openvario.cfg \
	file://cpufreq.cfg \
	file://usbhost.cfg \
	file://networking.cfg \
	file://netdev.cfg \
	file://usbnet.cfg \
	file://usbserial.cfg \
	file://usbstorage.cfg \
	file://filesystems.cfg \
	file://graphics.cfg \
	file://sound.cfg \
	file://wifi.cfg \
	file://bluetooth.cfg \
	file://w1.cfg \
	file://debloat.cfg \
"

SRC_URI:append:sunxi = " \
	file://0004-Set-minimum-CPU-voltage-to-1.1V.patch\
	\
	file://ov-cb2-common.dts \
	file://ov-cb2-am43.dts \
	file://ov-cb2-ch57s.dts \
	file://ov-cb2-ch57.dts \
	file://ov-cb2-am70s.dts \
	file://ov-cb2-ch70s.dts \
	file://ov-cb2-ch70.dts \
	file://ov-cb2-pq70.dts \
	file://ov-hdmi.dts \
	\
	file://sunxi.cfg \
	file://drm.cfg \
"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"
KCONFIG_MODE ?= "alldefconfig"
KMACHINE ?= "${MACHINE}"

# This kludge works around a failure to create
# "linux-*/meta/cfg/invalid.txt" because the "meta" directory does not
# exist.
KMETA = ".kernel-meta"

do_configure:prepend:sunxi() {
	cp ${UNPACKDIR}/ov-cb2-*.dts ${S}/arch/arm/boot/dts/
	cp ${UNPACKDIR}/ov-hdmi.dts ${S}/arch/arm/boot/dts/
}

INSANE_SKIP:${PN}-src += "buildpaths"

FILES_${KERNEL_PACKAGE_NAME}-base:append = " ${nonarch_base_libdir}/modules/${KERNEL_VERSION}/modules.builtin.modinfo"
