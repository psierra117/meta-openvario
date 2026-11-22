SUMMARY = "Distribution of boot up and recovery itb's with kernel and boot up initramfs built in"
HOMEPAGE = "none"
LICENSE = "MIT"
# openvario-base-image.bb is without CheckSum:
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

require ov-revision.inc

inherit deploy

S = "${UNPACKDIR}"
# The ITB is assembled in ${B}; S only holds the .its source.
B = "${WORKDIR}/build"

SRC_URI = "\
        file://openvario-recovery.its \
        "

DEPENDS = "\
        dtc-native \
        virtual/kernel \
        openvario-recovery-initramfs \
        u-boot-mkimage-native \
        u-boot \
    "

do_configure[depends] += "virtual/kernel:do_deploy openvario-recovery-initramfs:do_image_complete"

# DEPENDS += "ov-revision.inc "

do_compile[deptask] = "do_rm_work"

## the next line can be toggled between new and old!
do_configure () {
	cp ${UNPACKDIR}/openvario-recovery.its ${B}/
	#new image
	dd if=${DEPLOY_DIR_IMAGE}/uImage of=${B}/Image bs=64 skip=1
    # Accept both the stable deploy symlink and the versioned rootfs artifact name.
    if [ -f ${DEPLOY_DIR_IMAGE}/openvario-base-initramfs-${MACHINE}.rootfs.cpio.gz ]; then
        cp -v ${DEPLOY_DIR_IMAGE}/openvario-base-initramfs-${MACHINE}.rootfs.cpio.gz ${B}/initramfs.cpio.gz
    elif [ -f ${DEPLOY_DIR_IMAGE}/openvario-base-initramfs-${MACHINE}.cpio.gz ]; then
        cp -v ${DEPLOY_DIR_IMAGE}/openvario-base-initramfs-${MACHINE}.cpio.gz ${B}/initramfs.cpio.gz
    else
        bbfatal "Missing initramfs artifact for ${MACHINE} in ${DEPLOY_DIR_IMAGE}"
    fi
        cp -v ${DEPLOY_DIR_IMAGE}/${MACHINE}.dtb ${B}/openvario.dtb
}

# do_compile () {
#     pwd  # only as WO for one action
# }

do_mkimage () {
    # show mkimage version:
    MK_IMAGE=mkimage
    $MK_IMAGE -V
    # Build ITB with provided config
    $MK_IMAGE -A arm -f ${B}/openvario-recovery.its ${B}/ov-recovery.itb
}

addtask mkimage after do_configure before do_install

do_deploy() {
    install -Dm0644 ${B}/ov-recovery.itb ${DEPLOYDIR}/ov-recovery.itb
}

addtask deploy after do_mkimage before do_build
do_mkimage[dirs] = "${B}"
