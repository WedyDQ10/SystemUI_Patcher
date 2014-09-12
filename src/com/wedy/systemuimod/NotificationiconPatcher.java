package com.wedy.systemuimod;

import android.content.res.XModuleResources;
import android.content.res.XResources.DimensionReplacement;
import android.util.TypedValue;
import android.view.View;
import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;

public class NotificationiconPatcher implements IXposedHookZygoteInit, IXposedHookInitPackageResources {
	private static XSharedPreferences preference = null;
	private static String MODULE_PATH = null;
	/*private static Context mGbContext;*/
	
	@Override
	public void initZygote(StartupParam startupParam) throws Throwable {
	preference = new XSharedPreferences(NotificationiconPatcher.class.getPackage().getName());
		MODULE_PATH = startupParam.modulePath;
	}

	@Override
	public void handleInitPackageResources(InitPackageResourcesParam resparam) throws Throwable {
		String s = preference.getString("list_key", "4");
		int i = Integer.parseInt(s);
		String sz3 = preference.getString("key_widthz3", "90");
		int iz3 = Integer.parseInt(sz3);
		String sbl = preference.getString("key_fullalert_et", "100");
		int ibl = Integer.parseInt(sbl);
		if (!resparam.packageName.equals("com.android.systemui"))
			return;

		XModuleResources modRes = XModuleResources.createInstance(MODULE_PATH, resparam.res);

		boolean isDataw = preference.getBoolean("key_dataw", false);
		if(isDataw){
			resparam.res.setReplacement("com.android.systemui", "drawable", "ic_notify_button_bg", modRes.fwd(R.drawable.ic_notify_button_bg));

		}
		boolean isBrig = preference.getBoolean("key_brigi", false);
		if(isBrig){
			resparam.res.setReplacement("com.android.systemui", "dimen", "status_bar_icon_drawing_alpha", modRes.fwd(R.dimen.status_bar_icon_drawing_alpha));

		}
		boolean isBigi = preference.getBoolean("key_bigi", false);
		if(isBigi){
			resparam.res.setReplacement("com.android.systemui", "dimen", "status_bar_icon_drawing_size", modRes.fwd(R.dimen.status_bar_icon_drawing_size));

		}
		boolean isHeighttool = preference.getBoolean("key_heighttool", false);
		if(isHeighttool){
			resparam.res.setReplacement("com.android.systemui", "dimen", "notification_panel_tools_row_height", modRes.fwd(R.dimen.notification_panel_tools_row_height));

		}
		boolean isHeighttoolz3 = preference.getBoolean("key_heighttoolz3", false);
		if(isHeighttoolz3){
			resparam.res.setReplacement("com.android.systemui", "dimen", "notification_panel_tool_button_width",
        		new DimensionReplacement(iz3 / resparam.res.getDisplayMetrics().scaledDensity, TypedValue.COMPLEX_UNIT_DIP));

		}
		
		
		
		boolean isTranz1 = preference.getBoolean("key_tranz1", false);
		if(isTranz1){
			resparam.res.setReplacement("com.android.systemui", "color", "system_ui_opaque_background", 0x00000000);
			resparam.res.setReplacement("com.android.systemui", "color", "system_ui_transparent_background", 0x00000000);

		}
		boolean isTranz144 = preference.getBoolean("key_tranz144", false);

		if(isTranz144){
			resparam.res.setReplacement("com.android.systemui", "integer", "config_maxToolItemsInARow", i);
			resparam.res.setReplacement("com.android.systemui", "integer", "config_maxToolItemsInGrid", 99);
		}
		boolean isNotab = preference.getBoolean("key_notab", false);

		if(isNotab){
			resparam.res.hookLayout("com.android.systemui", "layout", "somc_tabs_status_bar_expanded", new XC_LayoutInflated() {
			    @Override
			    public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
			    	View clock = (View) liparam.view.findViewById(
			    	liparam.res.getIdentifier("tabs", "id", "android"));
			    	clock.setVisibility(View.GONE);
			    }
			    }); 
		}
		boolean iskey_fullalert = preference.getBoolean("key_fullalert", false);

		if(iskey_fullalert){
			resparam.res.setReplacement("com.android.systemui", "integer", "config_batteryChargedAlertLevel", ibl);
		}
		
		
		boolean isJpntr = preference.getBoolean("key_transjpn", false);

		if(isJpntr){
			resparam.res.setReplacement("com.android.systemui", "string", "quick_settings_bluetooth_off_label", modRes.fwd(R.string.quick_settings_bluetooth_off_label));
			resparam.res.setReplacement("com.android.systemui", "string", "quick_settings_wifi_off_label", modRes.fwd(R.string.quick_settings_wifi_off_label));
			resparam.res.setReplacement("com.android.systemui", "string", "quick_settings_rotation_unlocked_label", modRes.fwd(R.string.quick_settings_rotation_unlocked_label));
			resparam.res.setReplacement("com.android.systemui", "string", "quick_settings_rotation_locked_label", modRes.fwd(R.string.quick_settings_rotation_locked_label));
		}
		/*boolean isWhitedog = preference.getBoolean("key_wd", false);
		if(isWhitedog){
			resparam.res.hookLayout("com.android.systemui", "layout", "signal_cluster_view", new XC_LayoutInflated() {
			@Override
			    public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
			    	ImageView wdog = (ImageView) liparam.view.findViewById(
			    	liparam.res.getIdentifier("mobile_signal", "id", "com.android.systemui"));
			    	wdog.setImageDrawable(mGbContext.getResources().getDrawable(
R.drawable.stat_sys_signal_4));
			    }
			    }); 
		}*/


	}

}
