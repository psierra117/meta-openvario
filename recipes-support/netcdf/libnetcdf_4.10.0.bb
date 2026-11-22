SUMMARY = "NetCDF C library"
DESCRIPTION = "C library for reading and writing network Common Data Form files"
HOMEPAGE = "https://www.unidata.ucar.edu/software/netcdf/"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=cbb22cd5ded182bbd11d88ea19479b58"

# GitHub archive tarballs are not stable (QA src-uri-bad); fetch the tag.
SRC_URI = "git://github.com/Unidata/netcdf-c.git;protocol=https;nobranch=1;tag=v${PV}"
SRCREV = "80c6d3fbe3ee5b80014b0078c17b737b7e14d80c"

DEPENDS = "zlib"

inherit cmake

# SkySight forecast files use the classic NetCDF C interface. Keep the
# embedded build limited to local classic/64-bit-offset files; NetCDF-4/HDF5,
# remote access, plugins, tools and tests are not needed by XCSoar.
EXTRA_OECMAKE = " \
    -DBUILD_SHARED_LIBS=ON \
    -DNETCDF_ENABLE_HDF5=OFF \
    -DNETCDF_ENABLE_CDF5=OFF \
    -DNETCDF_ENABLE_DAP=OFF \
    -DNETCDF_ENABLE_NCZARR=OFF \
    -DNETCDF_ENABLE_REMOTE_FUNCTIONALITY=OFF \
    -DNETCDF_ENABLE_BYTERANGE=OFF \
    -DNETCDF_ENABLE_LIBXML2=OFF \
    -DNETCDF_ENABLE_PLUGINS=OFF \
    -DNETCDF_ENABLE_FILTER_BLOSC=OFF \
    -DNETCDF_ENABLE_FILTER_BZ2=OFF \
    -DNETCDF_ENABLE_FILTER_SZIP=OFF \
    -DNETCDF_ENABLE_FILTER_ZSTD=OFF \
    -DCMAKE_DISABLE_FIND_PACKAGE_Bz2=ON \
    -DCMAKE_DISABLE_FIND_PACKAGE_Zstd=ON \
    -DNETCDF_BUILD_UTILITIES=OFF \
    -DNETCDF_ENABLE_TESTS=OFF \
    -DNETCDF_ENABLE_EXAMPLES=OFF \
"

# nc-config and the build settings are development metadata, not runtime
# payload. Keeping them out of the library package also avoids a shell runtime
# dependency solely for nc-config.
FILES:${PN}-dev += " \
    ${bindir}/nc-config \
    ${libdir}/libnetcdf.settings \
"
