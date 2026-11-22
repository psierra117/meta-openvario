# The NetCDF build embeds compiler flags, --sysroot and absolute sysroot
# library paths into its pkg-config, CMake and *-config helpers. Wrynose
# treats references to TMPDIR as a fatal QA error (buildpaths), so strip
# them. The *.settings files only describe the build host and are removed.
netcdf_strip_buildpaths() {
	rm -f ${D}${libdir}/*.settings
	for f in ${D}${libdir}/pkgconfig/*.pc ${D}${bindir}/*-config \
	         $(find ${D}${libdir}/cmake -type f 2>/dev/null); do
		[ -f "$f" ] || continue
		sed -i \
			-e 's#--sysroot=[^ ";]*##g' \
			-e 's#-f[a-z]*-prefix-map=[^ ";]*##g' \
			-e 's#${RECIPE_SYSROOT_NATIVE}##g' \
			-e 's#${RECIPE_SYSROOT}##g' \
			-e 's#${TMPDIR}/[^ ";]*##g' \
			"$f"
	done
}

do_install[postfuncs] += "netcdf_strip_buildpaths"
