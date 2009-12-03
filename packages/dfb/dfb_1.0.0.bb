DESCRIPTION = "a directfb sample"
DEPENDS = "directfb"
SECTION = "libs"
LICENSE = "GPL"

PR = "r0"

SRC_URI = " \
 file://dfb.c \
"

S = "${WORKDIR}"

CFLAGS += "-I/${STAGING_INCDIR}/directfb"

LDFLAGS += "-ldirectfb"

do_compile () {
   ${CC} ${CFLAGS} ${LDFLAGS} -o dfb dfb.c
}

do_install () {
   install -d ${D}${bindir}/
   install -m 0755 ${S}/dfb ${D}${bindir}/
}

FILES_${PN} = "${bindir}/dfb"
