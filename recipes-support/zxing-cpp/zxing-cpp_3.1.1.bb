SUMMARY = "C++ barcode library with QR code reading and writing support"
HOMEPAGE = "https://github.com/zxing-cpp/zxing-cpp"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fa818a259cbed7ce8bc2a22d35a464fc"

SRC_URI = "git://github.com/zxing-cpp/zxing-cpp.git;protocol=https;nobranch=1;tag=v${PV}"
SRCREV = "287c85df6f961c8efbfb5ffd736cd9457b8b890e"

inherit cmake

EXTRA_OECMAKE = " \
    -DBUILD_SHARED_LIBS=ON \
    -DZXING_WRITERS=OLD \
    -DZXING_ENABLE_1D=OFF \
    -DZXING_ENABLE_AZTEC=OFF \
    -DZXING_ENABLE_DATAMATRIX=OFF \
    -DZXING_ENABLE_MAXICODE=OFF \
    -DZXING_ENABLE_PDF417=OFF \
    -DZXING_C_API=OFF \
    -DZXING_EXAMPLES=OFF \
    -DZXING_DEPENDENCIES=LOCAL \
    -DZXING_DISABLE_PCH=ON \
"
