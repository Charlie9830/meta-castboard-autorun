DESCRIPTON = "Castboard Systemd autorun script"
LICENSE = "CLOSED"
PR = "r0"

CB_SCRIPTS_REV = "3cc90547bad02830086f121f12f9ff5a99e2a647"

SRC_URI = "git://github.com/Charlie9830/castboard_scripts.git;protocol=https;rev=${CB_SCRIPTS_REV};destsuffix=git;branch=master;" 

S = "${WORKDIR}/git"

inherit update-rc.d systemd

SYSTEMD_PACKAGES = "${PN}"
INITSCRIPT_PACKAGES = "${PN}"

SYSTEMD_SERVICE_${PN} = "castboard_start.service"

do_install () {
    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${S}/autorun/castboard_start.service ${D}${systemd_unitdir}/system

    install -d ${D}/${libexecdir}
    install -m 0755 ${S}/autorun/castboard_start.sh ${D}/${libexecdir}
}

FILES_${PN} += "${libexecdir}"
FILES_${PN} += "${systemd_system_unitdir}" 