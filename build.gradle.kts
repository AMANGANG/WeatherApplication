// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
 /*
//// TODO what is the meaning of alias?
//// TODO why are you not using the android application plugin directly here?
//// TODO How is it referring the toml file?
The purpose of creating an alias is to basically  simplify the usage of plugins across
different modules in a project. Instead of repeating the plugin ID and version in each module,
we can define an alias for the plugin in one place and then use the alias in the modules.
The reason for not using the Android application plugin directly is to
centralize the plugin versions in a single place.
The toml file is referred to using the libs object.
*/
    alias(libs.plugins.androidApplication) apply false
}