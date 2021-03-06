package com.socks.jiandan.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.socks.jiandan.R;

import java.io.File;

/**
 * Created by zhaokaiqiang on 15/4/17.
 */
public class ShareUtil {

	public static void shareText(Activity activity, String shareText) {

		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_TEXT,
				shareText);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		activity.startActivity(Intent.createChooser(intent, activity.getResources().getString(R
				.string.app_name)));
	}

	public static void sharePicture(Activity activity, String imgPath) {

		Intent intent = new Intent(Intent.ACTION_SEND);
		if (imgPath == null || imgPath.equals("")) {
			ShowToast.Short("分享图片不存在哦");
		} else {
			File f = new File(imgPath);
			if (f != null && f.exists() && f.isFile()) {
				intent.setType("image/jpg");
				intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(f));
			}
		}

		intent.putExtra(Intent.EXTRA_SUBJECT, activity.getResources().getString(R
				.string.app_name));
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		activity.startActivity(Intent.createChooser(intent, activity.getResources().getString(R
				.string.app_name)));
	}


}
