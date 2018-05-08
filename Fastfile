# More documentation about how to customize your build
# can be found here:
# https://docs.fastlane.tools
fastlane_version "1.109.0"

# This value helps us track success metrics for Fastfiles
# we automatically generate. Feel free to remove this line
# once you get things running smoothly!
generated_fastfile_id "0bb1cd38-b438-45c4-b6d5-8ed5d8c6bf79"

default_platform :android

# Fastfile actions accept additional configuration, but
# don't worry, fastlane will prompt you for required
# info which you can add here later
lane :beta do
  # build the release variant
  gradle(task: "assembleRelease")

  # upload to Beta by Crashlytics
  crashlytics(
    api_token: "40b99fff0cec639aa200f9efd2d8380a42d68fda",
    build_secret: "46b5ab6753c0fc0c65d67e42d3a3a067856ef499dc98b4f2d92b980fc5c563e7"
  )
end
