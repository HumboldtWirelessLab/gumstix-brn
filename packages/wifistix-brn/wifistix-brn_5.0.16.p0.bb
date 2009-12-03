DESCRIPTION = "Linux Driver for Marvel 88W8385 802.11b/g Wifi Module used in Gumstix daughtercards (BRN)"
SECTION = "base"
PRIORITY = "optional"
HOMEPAGE = "http://www.gumstix.com"
LICENSE = "GPL"
RDEPENDS = "kernel (${KERNEL_VERSION})"
DEPENDS = "virtual/kernel"
PR = "r2"
PROVIDES = "wifistix-brn"

SRC_URI = "http://www2.informatik.hu-berlin.de/~sombrutz/pub/cf8385-brn.tar.bz2 \
     "

S = "${WORKDIR}/cf8385-brn"

inherit module-base

EXTRA_OEMAKE = 'CONFIG_GUMSTIX=y CONFIG_DEBUG=n KVER=2.6 \
                KERNELDIR="${KERNEL_SOURCE}" ARCH="${TARGET_ARCH}" \
                CC="${KERNEL_CC}" EXTRA_CFLAGS="${CFLAGS}" \
                INSTALL_MOD_PATH="${D}"'

do_compile() {	
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
	oe_runmake
}

do_install() {	
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
	oe_runmake install
}

PACKAGES = "${PN}"
FILES_${PN} = "${base_libdir}/modules/"
