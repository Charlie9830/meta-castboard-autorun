DESCRIPTON = "Castboard Systemd autorun script"
LICENSE = "CLOSED"
PR = "r0"

CB_SCRIPTS_REV = "052365375c79e5f0e62c1c808424052688ebefb9"

SRC_URI = "git://github.com/Charlie9830/castboard_scripts.git;protocol=https;rev=${CB_SCRIPTS_REV};destsuffix=git;branch=master;" 

S = "${WORKDIR}/git"

inherit update-rc.d systemd

SYSTEMD_PACKAGES = "${PN}"
INITSCRIPT_PACKAGES = "${PN}"

SYSTEMD_SERVICE_${PN} = "castboard_start.service"

do_install () {
    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${S}/start/castboard_start.service ${D}${systemd_unitdir}/system

    install -d ${D}/${libexecdir}
    install -m 0755 ${S}/start/castboard_start.sh ${D}/${libexecdir}
}

FILES_${PN} += "${libexecdir}"
FILES_${PN} += "${systemd_system_unitdir}" 