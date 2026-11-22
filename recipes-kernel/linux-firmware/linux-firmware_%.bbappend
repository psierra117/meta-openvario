# linux-firmware-rtl8192 is provided by OE-Core itself since Wrynose
# (rtlwifi/rtl8192* and rtl_bt/rtl8192*). Defining it here again lists the
# package twice and leaves the rtl_bt files unshipped.

PACKAGES =+ "${PN}-rtl8712 "
LICENSE:${PN}-rtl8712 = "Firmware-rtlwifi_firmware"
FILES:${PN}-rtl8712 = " ${nonarch_base_libdir}/firmware/rtlwifi/rtl8712*.bin "
RDEPENDS:${PN}-rtl8712 += "${PN}-rtl-license"

# Keep all rtl8192 variants together in linux-firmware-rtl8192.
FILES:${PN}-rtl8192ce = " "
FILES:${PN}-rtl8192cu = " "
FILES:${PN}-rtl8192su = " "
