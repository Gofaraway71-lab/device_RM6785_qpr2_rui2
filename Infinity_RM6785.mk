#
# Copyright (C) 2021-2022 The superiorOS Project
#
# SPDX-License-Identifier: Apache-2.0
#

# Inherit from those products. Most specific first.
$(call inherit-product, $(SRC_TARGET_DIR)/product/core_64_bit.mk)
$(call inherit-product, $(SRC_TARGET_DIR)/product/full_base_telephony.mk)
$(call inherit-product, $(SRC_TARGET_DIR)/product/non_ab_device.mk)

# Inherit from device makefile
$(call inherit-product, device/realme/RM6785/device.mk)

# Inherit some common superior stuff.
$(call inherit-product, vendor/infinity/config/common_full_phone.mk)


PERF_GOV_SUPPORTED := true
PERF_DEFAULT_GOV := schedutil

# Infinity-X flags
INFINITY_BUILD_TYPE := UNOFFICIAL
INFINITY_MAINTAINER := LUKS
TARGET_SUPPORTS_BLUR := true
TARGET_SUPPORTS_QUICK_TAP := true
TARGET_FACE_UNLOCK_SUPPORTED := true
PERF_ANIM_OVERRIDE := true

# Gapps
WITH_GAPPS := true# Infinity-X flags
INFINITY_BUILD_TYPE := UNOFFICIAL
INFINITY_MAINTAINER := Gofaraway71
TARGET_SUPPORTS_BLUR := true
TARGET_SUPPORTS_QUICK_TAP := true
TARGET_FACE_UNLOCK_SUPPORTED := true
PERF_ANIM_OVERRIDE := true


# GMS
WITH_GAPPS := true
WITH_GMS := true

# LOS Prebuilts
TARGET_INCLUDES_LOS_PREBUILTS := true

# Blur
TARGET_ENABLE_BLUR := true

# BCR
TARGET_PREBUILT_BCR := false

# Boot animation
TARGET_BOOT_ANIMATION_RES := 1920

# Perf Activity Anim Override
PERF_ANIM_OVERRIDE := true

# Device identifier. This must come after all inclusions.
PRODUCT_NAME := infinity_RM6785
PRODUCT_DEVICE := RM6785
PRODUCT_BRAND := realme
PRODUCT_MODEL := RM6785
PRODUCT_MANUFACTURER := realme
PRODUCT_GMS_CLIENTID_BASE := android-oppo

PRODUCT_BUILD_PROP_OVERRIDES += \
    BuildDesc=$(call normalize-path-list, "sys_oplus_mssi_64_cn-user 11 RP1A.200720.011 1623809323039 release-keys")

    BUILD_FINGERPRINT := realme/RMX2002/RMX2002L1:11/RP1A.200720.011/1651754371157:user/release-keys

PRODUCT_PRODUCT_PROPERTIES += \
   ro.build.fingerprint=$(BUILD_FINGERPRINT)
