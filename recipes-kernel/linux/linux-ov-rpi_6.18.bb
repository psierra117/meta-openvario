DESCRIPTION = "Linux Kernel for Openvario Raspberry Pi (6.18 LTS, opt-in)"
SECTION = "kernel"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

# 6.12 stays the default kernel. Select this one per build with
#   PREFERRED_VERSION_linux-ov-rpi = "6.18%"
# in conf/local.conf.
DEFAULT_PREFERENCE = "-1"

COMPATIBLE_MACHINE ?= "^rpi$"

PE = "1"
PV = "${LINUX_VERSION}+git${SRCPV}"

LINUX_VERSION ?= "6.18.64"
LINUX_RPI_BRANCH ?= "rpi-6.18.y"
LINUX_RPI_KMETA_BRANCH ?= "yocto-6.18"

SRCREV_machine = "a40f735325391b1705161b07f36ed45e1bf04521"
SRCREV_meta = "a5384458147b581e6beac32ee5cec4e4422d58b1"

inherit siteinfo
require recipes-kernel/linux/linux-yocto.inc

SRC_URI = "git://github.com/raspberrypi/linux.git;name=machine;branch=${LINUX_RPI_BRANCH};protocol=https \
           git://git.yoctoproject.org/yocto-kernel-cache;type=kmeta;name=meta;branch=${LINUX_RPI_KMETA_BRANCH};destsuffix=kernel-meta;protocol=https \
           file://powersave.cfg \
           file://lvds_bridge.cfg \
           file://debloat.cfg \
           file://console.cfg \
           ${@bb.utils.contains("INITRAMFS_IMAGE_BUNDLE", "1", "file://initramfs-image-bundle.cfg", "", d)} \
           ${@bb.utils.contains("MACHINE_FEATURES", "vc4graphics", "file://vc4graphics.cfg", "", d)} \
           ${@bb.utils.contains("MACHINE_FEATURES", "wm8960", "file://wm8960.cfg", "", d)} \
           file://default-cpu-governor.cfg \
           file://rpi4-nvmem.cfg \
           file://0001-ARM-dts-overlays-add-openvario-overlay.patch \
           file://ov-cm4-ch70-overlay.dts;subdir=git/arch/arm/boot/dts/overlays \
           file://ov-cm4-ch57-overlay.dts;subdir=git/arch/arm/boot/dts/overlays \
           file://ov-cm4-pq70-overlay.dts;subdir=git/arch/arm/boot/dts/overlays \
           file://ov-rpi4-ch57-overlay.dts;subdir=git/arch/arm/boot/dts/overlays \
           "

KCONFIG_MODE = "--alldefconfig"
KBUILD_DEFCONFIG:raspberrypi4 ?= "bcm2711_defconfig"
KBUILD_DEFCONFIG:raspberrypi4-64 ?= "bcm2711_defconfig"

LINUX_VERSION_EXTENSION ?= ""

KERNEL_MODULE_AUTOLOAD += "${@bb.utils.contains("MACHINE_FEATURES", "pitft28r", "stmpe-ts", "", d)}"
KERNEL_MODULE_AUTOLOAD:rpi += "i2c-dev i2c-bcm2708"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"
UBOOT_ENTRYPOINT =       "0x00008000"
UBOOT_LOADADDRESS =      "0x00008000"

KERNEL_DTC_FLAGS += "-@ -H epapr"

RDEPENDS:${KERNEL_PACKAGE_NAME}:raspberrypi-armv7:append = " ${RASPBERRYPI_v7_KERNEL_PACKAGE_NAME}"
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:raspberrypi-armv7:append = " ${RASPBERRYPI_v7_KERNEL_PACKAGE_NAME}-base"
RDEPENDS:${KERNEL_PACKAGE_NAME}-image:raspberrypi-armv7:append = " ${RASPBERRYPI_v7_KERNEL_PACKAGE_NAME}-image"
RDEPENDS:${KERNEL_PACKAGE_NAME}-dev:raspberrypi-armv7:append = " ${RASPBERRYPI_v7_KERNEL_PACKAGE_NAME}-dev"
RDEPENDS:${KERNEL_PACKAGE_NAME}-vmlinux:raspberrypi-armv7:append = " ${RASPBERRYPI_v7_KERNEL_PACKAGE_NAME}-vmlinux"
RDEPENDS:${KERNEL_PACKAGE_NAME}-modules:raspberrypi-armv7:append = " ${RASPBERRYPI_v7_KERNEL_PACKAGE_NAME}-modules"
RDEPENDS:${KERNEL_PACKAGE_NAME}-dbg:raspberrypi-armv7:append = " ${RASPBERRYPI_v7_KERNEL_PACKAGE_NAME}-dbg"

DEPLOYDEP = ""
DEPLOYDEP:raspberrypi-armv7 = "${RASPBERRYPI_v7_KERNEL}:do_deploy"
do_deploy[depends] += "${DEPLOYDEP}"
