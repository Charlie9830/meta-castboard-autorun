DESCRIPTON = "Castboard Systemd autorun script"
LICENSE = "CLOSED"
PR = "r0"

SRC_URI = " \ 
    file://cage@.service \
    file://cage \
    file://default.target \
    " 

S = "${WORKDIR}"

inherit update-rc.d systemd useradd

SYSTEMD_PACKAGES = "${PN}"
INITSCRIPT_PACKAGES = "${PN}"

SYSTEMD_SERVICE_${PN} = "cage@.service"\

# Create the 'cage' System user.
USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "--system -d /home/cage cage "

do_install () {
    # Install our main Unit File.
    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${S}/cage@.service ${D}${systemd_unitdir}/system

    # Install our PAM conf file.
    install -d ${D}${sysconfdir}/pam.d/
    install -m 0644 ${S}/cage ${D}${sysconfdir}/pam.d/

    # Install a home directory for the cage user. Castboard will want to put it's logs and data files here.
    install -d ${D}/home/cage
    chown -R cage ${D}/home/cage
}

FILES_${PN} += "${libexecdir}"
FILES_${PN} += "${systemd_system_unitdir}"
FILES_${PN} += " \
    /home/cage \
"

CONFFILES_${PN} = " \
    ${sysconfdir}/pam.d/cage \
"