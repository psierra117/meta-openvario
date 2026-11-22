SUMMARY = "NetCDF C++ library"
DESCRIPTION = "C++ interface to the NetCDF C data format library"
HOMEPAGE = "https://www.unidata.ucar.edu/software/netcdf/"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=7a265567ba44537b8d1ed8b406d4b25c"

DEPENDS = "libnetcdf"

SRC_URI = " \
    git://github.com/Unidata/netcdf-cxx4.git;protocol=https;nobranch=1;tag=v${PV} \
    file://0002-fix-ncgroup-array-delete.patch \
"
SRCREV = "f8882188267488ef801691e69ad072e3eb217ad8"

# Autotools produces libnetcdf_c++4, matching OpenSoar's system-library path.
# It also avoids CMake's unconditional HDF5 lookup for our classic-only build.
inherit autotools pkgconfig

EXTRA_OECONF = " \
    --with-nc-config=no \
    --disable-filter-testing \
    --disable-static \
    --disable-doxygen \
"

# Keep build helpers and settings in the development package.
require netcdf-buildpaths.inc

FILES:${PN}-dev += " \
    ${bindir}/ncxx4-config \
"
