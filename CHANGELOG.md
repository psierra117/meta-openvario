# Changelog

## meta-openvario 6.0.0

**Yocto 6.0 Wrynose · Linux 6.18 LTS · XCSoar 7.46.1 · OpenSoar 7.46.26.1**

### What's Changed

- xcsoar: update to 7.45.3 by [@Scumi](https://github.com/Scumi) in [#458](https://github.com/Openvario/meta-openvario/pull/458)
- xcsoar-testing: update Boost to 1.92.0 by [@Scumi](https://github.com/Scumi) in [#459](https://github.com/Openvario/meta-openvario/pull/459)
- Various boot and app improvements (OpenSoar 7.44.24.2, startup messages, data storage recovery, display settings sync, reproducible builds) by [@Scumi](https://github.com/Scumi) in [#461](https://github.com/Openvario/meta-openvario/pull/461)
- Added OV-CM4-CH70 by [@psierra117](https://github.com/psierra117) in [#463](https://github.com/Openvario/meta-openvario/pull/463)
- linux: Enable the USB audio driver by [@lordfolken](https://github.com/lordfolken) in [#465](https://github.com/Openvario/meta-openvario/pull/465)
- xcsoar: Install DejaVu Sans Mono by [@lordfolken](https://github.com/lordfolken) in [#466](https://github.com/Openvario/meta-openvario/pull/466)
- XCSoar system tools (sensor calibration, touchscreen calibration, system information) and XCSoar 7.46.1 by [@Scumi](https://github.com/Scumi) in [#462](https://github.com/Openvario/meta-openvario/pull/462)
- u-boot: Boot in portrait by default by [@lordfolken](https://github.com/lordfolken) in [#468](https://github.com/Openvario/meta-openvario/pull/468)
- xcsoar-profiles: OpenVario panel defaults, dark mode, bottom 8 InfoBox layout with vario, drop keys matching XCSoar defaults by [@lordfolken](https://github.com/lordfolken)
- image: Record the tag form used in the image name by [@lordfolken](https://github.com/lordfolken)
- Include German glibc locale in openvario-base-image by [@psierra117](https://github.com/psierra117)
- **Migration to Yocto 6.0 Wrynose** (LTS until April 2030): bitbake 2.18, GCC 15.3, glibc 2.43, Mesa 26, U-Boot 2026.01; distro features, recipes (`UNPACKDIR`), patches (`Upstream-Status`), fetchers, packaging and QA adapted; U-Boot RGB-swap patch rebased; lima instead of the removed Mesa kmsro driver on Cubieboard2; no gold linker for XCSoar/OpenSoar; boot partition labels limited to 11 characters; `/home/root` kept as home directory by [@psierra117](https://github.com/psierra117)
- Linux 6.18.64 LTS as default kernel for Raspberry Pi / CM4 and Cubieboard2 (6.12 still selectable) by [@psierra117](https://github.com/psierra117)
- opensoar: update to release 7.46.26.1 by [@psierra117](https://github.com/psierra117)
- ovmenu-ng: rotate touch input with the XCSoar / OpenSoar display orientation by [@psierra117](https://github.com/psierra117)
- xcsoar, opensoar: fix mouse direction with rotated display orientations by [@psierra117](https://github.com/psierra117)
- ovmenu-ng: add Reboot and Shutdown to the start menu by [@psierra117](https://github.com/psierra117)
- ov-system-init: never format a data partition that has a filesystem by [@psierra117](https://github.com/psierra117)
- update-system-config: keep ~/.xcsoar unless the restore succeeded by [@psierra117](https://github.com/psierra117)
- xcsoar-profiles: listen on the sensord port 4353 by [@psierra117](https://github.com/psierra117)
- ovlinux: derive the package feed architecture from the machine tune by [@psierra117](https://github.com/psierra117)
- rpi-config: remove stray space in max98357a overlay parameter by [@psierra117](https://github.com/psierra117)
- u-boot: find the ov-cb2-hdmi files again by [@psierra117](https://github.com/psierra117)
- OpenVario 6.0, revision r60, README and CHANGELOG by [@psierra117](https://github.com/psierra117)

### Tested hardware

- `ov-cm4-ch70`: tested on hardware
- all other machines: build successfully, not yet tested on hardware — test reports are welcome

**Full Changelog**: [`d439cb0...6.0.0`](https://github.com/psierra117/meta-openvario/compare/d439cb04df0ecf06e531f259559da57b83bf2ce6...6.0.0)

### Contributors

[@Scumi](https://github.com/Scumi), [@lordfolken](https://github.com/lordfolken), [@psierra117](https://github.com/psierra117), Uwe Augustin and all contributors to OpenVario, XCSoar and OpenSoar.
