DESCRIPTON = "Castboard Systemd autorun script"
LICENSE = "CLOSED"
PR = "r0"

CB_SCRIPTS_REV = "b3fc48c3254be1d35f933244ee5e4d9571b36f74"

SRC_URI = "file://castboard_start.service" 

S = "${WORKDIR}"

inherit update-rc.d systemd

SYSTEMD_PACKAGES = "${PN}"
INITSCRIPT_PACKAGES = "${PN}"

SYSTEMD_SERVICE_${PN} = "castboard_start.service"

do_install () {
    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${S}/castboard_start.service ${D}${systemd_unitdir}/system
}

FILES_${PN} += "${libexecdir}"
FILES_${PN} += "${systemd_system_unitdir}" 