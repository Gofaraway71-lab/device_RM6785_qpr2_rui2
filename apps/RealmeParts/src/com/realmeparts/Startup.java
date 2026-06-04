/*
 * Copyright (C) 2013 The OmniROM Project
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.realmeparts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;


public class Startup extends BroadcastReceiver {

    private static final String TAG = "BootReceiver";

    private void restore(String file, boolean enabled) {
        if (file == null) {
            return;
        }
        if (enabled) {
            Utils.writeValue(file, "1");
        }
    }

    private void restore(String file, String value) {
        if (file == null) {
            return;
        }
        Utils.writeValue(file, value);
    }

    @Override
    public void onReceive(final Context context, final Intent bootintent) {

        boolean enabled = false;
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        enabled = sharedPrefs.getBoolean(DeviceSettings.KEY_OTG_SWITCH, false);
        restore(OTGModeSwitch.getFile(), enabled);
        enabled = sharedPrefs.getBoolean(DeviceSettings.KEY_GAME_SWITCH, false);
        restore(GameModeSwitch.getFile(), enabled);		
        enabled = sharedPrefs.getBoolean(DeviceSettings.KEY_DT2W_SWITCH, false);
        restore(DT2WModeSwitch.getFile(), enabled);
        enabled = sharedPrefs.getBoolean(DeviceSettings.KEY_CHARGING_SWITCH, false);
        if (enabled) {
            Utils.startService(context, SmartChargingService.class);
        }
        enabled = sharedPrefs.getBoolean(DeviceSettings.KEY_FPS_INFO, false);
        if (enabled) {
            Utils.startService(context, FPSInfoService.class);
        }
        if (Utils.fileExists(DeviceSettings.FLASHLIGHT_BRIGHTNESS_NODE)) {
            int brightness = sharedPrefs.getInt(DeviceSettings.KEY_FLASHLIGHT_BRIGHTNESS, 1);
            Utils.setValue(DeviceSettings.FLASHLIGHT_BRIGHTNESS_NODE, brightness);
        }

        boolean refresh90 = sharedPrefs.getBoolean("refresh_rate_90", true);
        boolean refresh90Forced = sharedPrefs.getBoolean("refresh_rate_90Forced", false);

        RefreshRateSwitch.setPeakRefresh(context, refresh90);

        if (refresh90Forced) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
                RefreshRateSwitch.setForcedRefreshRate(1);
            } else {
                RefreshRateSwitch.setForcedRefreshRate(0);
            }
        }
    }

}
