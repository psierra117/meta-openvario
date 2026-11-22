# meta-openvario

**The Yocto/OpenEmbedded layer for the OpenVario flight computer — now on Yocto Project 6.0 "Wrynose" with Linux 6.18 LTS.**

![Yocto](https://img.shields.io/badge/Yocto-6.0%20Wrynose%20(LTS)-0a66c2)
![Linux](https://img.shields.io/badge/Linux-6.18%20LTS-f0a30a)
![XCSoar](https://img.shields.io/badge/XCSoar-7.46.1-2e8b57)
![OpenSoar](https://img.shields.io/badge/OpenSoar-7.46.26.1-2e8b57)
![OpenVario](https://img.shields.io/badge/OpenVario-6.0-6f42c1)

`meta-openvario` contains everything that turns a generic embedded Linux into OpenVario Linux: machine
definitions for the Cubieboard2 and Raspberry Pi CM4 based OpenVario devices, the kernel and bootloader
configuration, the start menu, system services and the glide computer applications XCSoar and OpenSoar.

This layer is not built on its own. The complete build environment, with all layers pinned to tested
revisions, lives in the [OpenVario](https://github.com/psierra117/OpenVario/tree/wrynose) repository.

| Repository | Purpose |
|---|---|
| [psierra117/meta-openvario](https://github.com/psierra117/meta-openvario/tree/wrynose) | This layer: recipes, machines, distribution |
| [psierra117/OpenVario](https://github.com/psierra117/OpenVario/tree/wrynose) | Build environment: all layers as submodules, build instructions |

---

## OpenVario 6.0 at a glance

OpenVario 6.0 moves the platform from Yocto 5.0 "Scarthgap" to **Yocto 6.0 "Wrynose"**, the current
long-term-support release, maintained until April 2030.

| Component | OpenVario 5.1 | OpenVario 6.0 |
|---|---|---|
| Yocto Project | 5.0 Scarthgap | **6.0 Wrynose (LTS)** |
| Linux kernel (CM4) | 6.12.79 | **6.18.64 LTS** |
| Linux kernel (Cubieboard2) | 6.12.79 | **6.18.64 LTS** |
| U-Boot | 2024.01 | **2026.01** |
| Toolchain | GCC 13, glibc 2.39 | **GCC 15.3, glibc 2.43** |
| Mesa | 24.0 | **26.0** |
| XCSoar | 7.45.1 | **7.46.1** |
| OpenSoar | 7.43 (3.23.8) | **7.46.26.1** |

Highlights:

- **New platform base** — Wrynose LTS with current toolchain, libraries and security fixes.
- **Linux 6.18 LTS** on all OpenVario devices.
- **Touch follows the display orientation** — rotate the screen in XCSoar or OpenSoar and touch input
  rotates with it, live. A connected mouse moves correctly in every orientation.
- **Reboot and Shutdown** directly from the start menu.
- **Safer data partition** — an existing data partition is never formatted automatically; flights and
  settings survive reflashing and mount errors.
- **German language support** out of the box.
- **OpenVario CM4-CH70** supported as a machine.

The full list of changes is in [CHANGELOG.md](CHANGELOG.md).

---

## Supported hardware

| Machine | Device | Kernel | Status |
|---|---|---|---|
| `ov-cm4-ch70` | Raspberry Pi CM4, 7" CH70 display | linux-ov-rpi 6.18 | **Tested on hardware** |
| `ov-cm4-ch57` | Raspberry Pi CM4, 5.7" CH57 display | linux-ov-rpi 6.18 | Builds, hardware test pending |
| `ov-cm4-pq70` | Raspberry Pi CM4, 7" PQ70 display | linux-ov-rpi 6.18 | Builds, hardware test pending |
| `ov-cb2-ch70` | Cubieboard2, 7" CH70 display | linux-openvario 6.18 | Builds, hardware test pending |
| `ov-cb2-ch57` | Cubieboard2, 5.7" CH57 display | linux-openvario 6.18 | Builds, hardware test pending |
| `ov-cb2-pq70` | Cubieboard2, 7" PQ70 display | linux-openvario 6.18 | Builds, hardware test pending |
| `ov-cb2-am43` | Cubieboard2, 4.3" AM43 display | linux-openvario 6.18 | Builds, hardware test pending |
| `ov-cb2-am70s` | Cubieboard2 + DS2 adapter, 7" AM70 | linux-openvario 6.18 | Builds, hardware test pending |
| `ov-cb2-ch57s` | Cubieboard2 + DS2 adapter, 5.7" CH57 | linux-openvario 6.18 | Builds, hardware test pending |
| `ov-cb2-ch70s` | Cubieboard2 + DS2 adapter, 7" CH70 | linux-openvario 6.18 | Builds, hardware test pending |
| `ov-cb2-hdmi` | Cubieboard2, HDMI output | linux-openvario 6.18 | Builds, hardware test pending |
| `ov-rpi4`, `ov-rpi4-64` | Raspberry Pi 4 | linux-ov-rpi 6.18 | Development only |

Feedback from pilots with Cubieboard2 or CM4-CH57/PQ70 devices is very welcome — please report your
results in the [issues](https://github.com/psierra117/meta-openvario/issues).

---

## Layer dependencies

The `wrynose` branch is tested against these revisions (pinned as submodules in
[OpenVario](https://github.com/psierra117/OpenVario/tree/wrynose)):

| Layer | Branch | Revision |
|---|---|---|
| bitbake | 2.18 | `6e260eb` |
| openembedded-core | wrynose | `16a03ed` |
| meta-openembedded (meta-oe, meta-networking, meta-python) | wrynose | `c0fcd7c` |
| meta-arm | wrynose | `673e8d7` |
| meta-raspberrypi | wrynose | `9195883` |
| meta-rauc | wrynose | `38da0e5` |
| meta-sunxi | master | `a086787` (last revision compatible with Wrynose) |

## Branches

| Branch | Yocto base | Use |
|---|---|---|
| `wrynose` | 6.0 Wrynose | **OpenVario 6.x — current development and releases** |
| `scarthgap` | 5.0 Scarthgap | OpenVario 5.x, kept for reference |

Release tags follow `<major>.<release>.<fix>`, where the major number is the Yocto base
(`5` = Scarthgap, `6` = Wrynose). The tag becomes part of the image name, e.g.
`OV-6.0.0-CM4-CH70.wic.bz2`.

---

## Building

Use the [OpenVario](https://github.com/psierra117/OpenVario/tree/wrynose) repository:

```sh
git clone --recurse-submodules -b wrynose https://github.com/psierra117/OpenVario.git
cd OpenVario
docker run -it --rm -v $(pwd):/workdir ghcr.io/openvario/ovbuild-container:latest --workdir=/workdir

# inside the container
source openembedded-core/oe-init-build-env .
MACHINE=ov-cm4-ch70 bitbake openvario-image
```

Images are written to `tmp/deploy/images/<machine>/`.

### Kernel selection

Linux 6.18 LTS is the default on every machine. The previous 6.12 kernels remain available, e.g. for
comparison, by adding one line to `conf/local.conf`:

```sh
PREFERRED_VERSION_linux-ov-rpi = "6.12%"      # Raspberry Pi / CM4
PREFERRED_VERSION_linux-openvario = "6.12%"   # Cubieboard2
```

---

## Runtime notes

### Data partition

The third partition of the SD card holds flights, profiles, maps and settings (`/home/root/data`).
It is created on first boot and **kept when a new image is flashed**. If it cannot be mounted, OpenVario
runs a filesystem check and otherwise stops with an error message — it never formats a partition that
already contains a filesystem.

### Touch and display orientation

XCSoar and OpenSoar rotate touch input together with their own display orientation setting, so no
changes to `config.txt` are needed when the screen is rotated. The touch driver must report coordinates
for the default (landscape) orientation, which is the shipped configuration.

Devices whose touch driver already rotates coordinates can disable this in `/boot/config.uEnv`:

```sh
xcsoar_input_transform=system
```

### Start menu

When XCSoar or OpenSoar is closed, the start menu offers **OpenSoar**, **XCSoar**, **Terminal**,
**Reboot** and **Shutdown**. Reboot and Shutdown ask for confirmation (default: No). All other settings
are made inside XCSoar / OpenSoar.

### Package feed

`opkg` is configured for the architecture of the device (`cortexa72` on CM4, `cortexa7t2hf-neon` on
Cubieboard2) and the feed `https://repo.openvario.de/feed-wrynose`. Packages built for Scarthgap are not
compatible with OpenVario 6.

---

## Contributing

Bug reports, test results and pull requests are welcome:

- [Issues](https://github.com/psierra117/meta-openvario/issues)
- Pull requests against the `wrynose` branch

Please describe the machine (`ov-…`), the image version (`OV-6.x.y-…`) and how to reproduce the problem.

## Credits

OpenVario is a community project. This branch builds on the work of the
[OpenVario](https://github.com/Openvario) project and everyone who contributed to meta-openvario,
XCSoar, OpenSoar, sensord and variod — thank you.

## License

The recipes and configuration in this layer are released under the MIT license unless a file states
otherwise. The software built by the recipes (Linux, U-Boot, XCSoar, OpenSoar, …) keeps its own license,
declared in the respective recipe.
