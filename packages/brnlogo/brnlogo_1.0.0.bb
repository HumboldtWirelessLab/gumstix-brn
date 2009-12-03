DESCRIPTION = "The brnlogo"
DEPENDS = "directfb"
SECTION = "libs"
LICENSE = "GPL"

PR = "r0"

SRC_URI = " \
 file://brnlogo.c \
 file://brnlogo.png \
 
"

S = "${WORKDIR}"

CFLAGS += "-I/${STAGING_INCDIR}/directfb"

LDFLAGS += "-ldirectfb"

do_compile () {
   ${CC} ${CFLAGS} ${LDFLAGS} -o brnlogo brnlogo.c
}

do_install () {
   install -d ${D}${bindir}/
   install -d ${D}${datadir}/
   install -d ${D}${datadir}/brn/
   install -m 0755 ${S}/brnlogo ${D}${bindir}/
   install -m 0644 ${S}/brnlogo.png ${D}${datadir}/brn/
}

FILES_${PN} = "${bindir}/brnlogo ${datadir}/brn/brnlogo.png"
