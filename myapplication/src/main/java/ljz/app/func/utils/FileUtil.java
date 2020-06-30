package ljz.app.func.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;


public class FileUtil {
	public static final String TAG = "FileUtil";
	/***
	 * 4.4以下(也就是kitkat以下)的版本
	 */
	public static final int KITKAT_LESS = 104;
	/***
	 * 4.4以上(也就是kitkat以上)的版本,当然也包括最新出的5.0棒棒糖
	 */
	public static final int KITKAT_ABOVE = 105;
	public static final String SDCARD = Environment
			.getExternalStorageDirectory().getPath() + "/";

	public static FileUtil fileUtil;


	public static FileUtil getInstance() {
		if (fileUtil == null) {
			fileUtil = new FileUtil();
		}
		return fileUtil;
	}



	/**
	 * 在SD卡上创建文件
	 * 
	 * @throws IOException
	 */
	public File creatSDFile(String fileName) throws IOException {
		File file = new File(SDCARD + fileName);
		file.createNewFile();
		return file;
	}

	/**
	 * 在SD卡上创建目录
	 * 
	 * @param dirName
	 */
	public File creatSDDir(String dirName) {
		File dir = new File(SDCARD + dirName);
		dir.mkdir();
		return dir;
	}

	/**
	 * 判断SD卡上的文件夹是否存在
	 */
	public boolean isFileExist(String fileName) {
		File file = new File(SDCARD + fileName);
		return file.exists();
	}

	/**
	 * 将一个InputStream里面的数据写入到SD卡中
	 */
	public File write2SDFromInput(String path, String fileName,
			InputStream input) {
		File file = null;
		OutputStream output = null;
		try {
			creatSDDir(path);
			file = creatSDFile(path + fileName);
			output = new FileOutputStream(file);
			byte buffer[] = new byte[4 * 1024];
			while ((input.read(buffer)) != -1) {
				output.write(buffer);
			}
			output.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			SafeCloseUtils.close(output);
		}
		return file;
	}

	/**
	 * 文件是否存在
	 * @param filePath
	 * @return
	 */
	public static boolean isFileExist2(String filePath) {
		File f = new File(filePath);
		return f.exists() && f.isFile();
	}

	/**
	 * 目录是否存在
	 * @param dirPath
	 * @return
	 */
	public static boolean isDirExist(String dirPath) {
		File f = new File(dirPath);
		return f.exists() && f.isDirectory();
	}
	
	/**
	 * 创建文件
	 * 
	 * @param path
	 *            文件路径
	 * @param filename
	 *            文件名
	 * @return
	 */
	public static boolean createFile(String path, String filename) {
		if(!createDir(path)) {
			return false;
		}
		String filePath = path +File.separator+ filename;
		File file = new File(filePath);
		if (!file.exists() || !file.isFile()) {
			try {
				// 创建新文件
				return file.createNewFile();
			} catch (Exception e) {
//				Logs.e(TAG, e.getMessage(), e);
			}
		}
		return true;
	}
	
	/**
	 * 创建目录
	 * @param dirPath
	 * @return
	 */
	public static boolean createDir(String dirPath) {
		File file = new File(dirPath);
		if (!file.exists() || !file.isDirectory()) {
			return file.mkdirs();
		}
		return true;
	}
	
	/**
	 * 创建文件
	 * @param filePath 文件绝对路径
	 * @return
	 */
	public static boolean createFile(String filePath) {
		String pathName = getPathName(filePath);
		String fileName = getFileName(filePath);
		return createFile(pathName, fileName);
	}

	/**
	 * @Description 文件重命名
	 * @param
	 * @return
	 * @Author zhaoqianpeng(zqp@yitong.com.cn) 2014-4-15
	 */
	public static boolean renameTo(String oldPath, String newPath){
		boolean renameFlg = false;
		File ofile = new File(oldPath);
		if(ofile.exists()){
			File nfile = new File(newPath);
			renameFlg = ofile.renameTo(nfile);
		}
		return renameFlg;
	}
	
	/**
	 * @Description 删除指定路径文件
	 * @param
	 * @Author zhaoqianpeng(zqp@yitong.com.cn) 2014-4-15
	 */
	public static void deleteFile(String filePath) {
		File file = new File(filePath);
		// 判断文件是否存在
		if (file.exists() && file.isFile()) { 
			//删除
			file.delete(); 
		}
	}
	
	/**
	 * 删除指定目录下的所有文件
	 * @param dirPath
	 */
	public static boolean deleteFiles(String dirPath) {
		File fi = new File(dirPath);
		if(fi.exists() && fi.isDirectory()) {
			File[] files = fi.listFiles();
			for(File f : files) {
				if(f.exists()) {
					if(f.isFile()) {
						if(!f.delete()) {
							return false;
						}
					} else {
						// 文件为目录的情况，需要进行递归删除
						deleteFiles(f.getAbsolutePath());
					}
				}
			}
		}
		return true;
	}

	public static void cache(String path, byte[] data) throws IOException {
		OutputStream os = null;
		try {
			os = new FileOutputStream(path);
			os.write(data);
		} catch (IOException e) {
			Log.w(TAG, "file cache(" + path + ") error!");
		} finally {
			if (null != os)
				os.close();
			os = null;
		}
	}

	public static boolean checkIsImgFile(String path) {
		boolean isImgFile = false;
		// 获取扩展名
		String fileEnd = path.substring(path.lastIndexOf(".") + 1,
				path.length()).toLowerCase();
		if (fileEnd.equals("png") || fileEnd.equals("jpg")) {
			isImgFile = true;
		} else {
			isImgFile = false;
		}
		return isImgFile;
	}

	public static boolean checkIsPdfFile(String path) {
		boolean isPdfFile = false;
		// 获取扩展名
		String fileEnd = path.substring(path.lastIndexOf(".") + 1,
				path.length()).toLowerCase();
		if (fileEnd.equals("pdf")) {
			isPdfFile = true;
		} else {
			isPdfFile = false;
		}
		return isPdfFile;
	}

	public static boolean checkIsVideoFile(String path) {
		boolean isVideoFile = false;
		// 获取扩展名
		String fileEnd = path.substring(path.lastIndexOf(".") + 1,
				path.length()).toLowerCase();
		if (fileEnd.equals("mp4") || fileEnd.equals("3gp")) {
			isVideoFile = true;
		} else {
			isVideoFile = false;
		}
		return isVideoFile;
	}

	public static String getFileName(String path) {
		String fileName = path.substring(path.lastIndexOf("/")).substring(1);
		return fileName;
	}
	
	public static String getPathName(String filePath) {
		return filePath.substring(0, filePath.lastIndexOf(File.separator));
	}

	public static Bitmap getDiskBitmap(String pathString) {
		Bitmap bitmap = null;
		try {
			File file = new File(pathString);
			if (file.exists()) {
				bitmap = BitmapFactory.decodeFile(pathString);
			}
		} catch (Exception e) {
		}
		return bitmap;
	}

	/**
	 * 获取系统缓存路径<br>
	 * 当SD卡存在或者SD卡不可被移除时，获取SD卡路径；否则获取内存路径
	 * @param context 上下文
	 * @return 缓存路径
	 */
	@SuppressLint("NewApi")
	public static String getDiskCachePath(Context context) {
		String cachePath = null;
		if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
			cachePath = context.getExternalCacheDir().getAbsolutePath();
		} else {
			cachePath = context.getCacheDir().getAbsolutePath();
		}
		return cachePath;
	}
	
	/**
	 * 获取系统文件路径<br>
	 * 当SD卡存在或者SD卡不可被移除时，获取SD卡路径；否则获取内存路径
	 * @param context 上下文
	 * @return 缓存路径
	 */
	@SuppressLint("NewApi")
	public static String getDiskFilePath(Context context) {
		String filePath = null;
		if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
			filePath = context.getExternalFilesDir(null).getAbsolutePath();
		} else {
			filePath = context.getFilesDir().getAbsolutePath();
		}
		return filePath;
	}
	
	public static void copyFile(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				// int length;
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();

		}

	}

	public static InputStream readFile(File file) {
		InputStream is = null;
		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			Log.d(TAG, "file not found: " + file.getAbsolutePath());
		}
		return is;
	}

	/**
	 * 获取文件byte
	 * 
	 * @param file
	 * @return
	 */
	public static byte[] getFileByte(File file) {
		byte[] buffer = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer;

	}

	/**
	 * 保存图片
	 * 
	 * @param bm
	 * @param
	 */
	public static void saveBitmap(String bmPath, String bmName, Bitmap bm) {
		FileOutputStream fos = null;
		try {
			File f = new File(bmPath, bmName);
			if (f.exists() && f.isFile()) {
				f.delete();
			}
			fos = new FileOutputStream(f);
			bm.compress(Bitmap.CompressFormat.JPEG, 90, fos);
			fos.flush();
		} catch (FileNotFoundException e) {
//			Logs.e(TAG, e.getMessage(), e);
		} catch (IOException e) {
//			Logs.e(TAG, e.getMessage(), e);
		} finally {
			bm.recycle();
			bm = null;
			try {
				if (null != fos) {
					fos.close();
					fos = null;
				}
			} catch (IOException e) {
//				Logs.w(TAG, "关闭FOS失败", e);
			}
		}
	}




	public File createTmpFile(Context context) {

		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {

			File pic = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
					Locale.CHINA).format(new Date());
			String fileName = "multi_image_" + timeStamp + "";
			File tmpFile = new File(pic, fileName + ".jpg");
			return tmpFile;
		} else {

			File cacheDir = context.getCacheDir();
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
					Locale.CHINA).format(new Date());
			String fileName = "multi_image_" + timeStamp + "";
			File tmpFile = new File(cacheDir, fileName + ".jpg");
			return tmpFile;
		}

	}

	public File createRecordFile(Context context) {

		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			File record = Environment.getExternalStorageDirectory();
			File tmpFile = new File(record, "record.m4a");
			return tmpFile;
		} else {
			File cacheDir = context.getCacheDir();
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
					Locale.CHINA).format(new Date());
			String fileName = "multi_image_" + timeStamp + "";
			File tmpFile = new File(cacheDir, fileName + ".amr");
			return tmpFile;
		}

	}

	/***
	 * 选择一张图片 图片类型，这里是image/*，当然也可以设置限制 如：image/jpeg等
	 *
	 * @param activity
	 *            Activity
	 */
	@SuppressLint("InlinedApi")
	public void selectPicture(Activity activity) {
		if (Build.VERSION.SDK_INT < 19) {
			Intent intent = new Intent();
			intent.setType("image/*");
			intent.setAction(Intent.ACTION_GET_CONTENT);
			activity.startActivityForResult(intent, KITKAT_LESS);
		} else {
			Intent intent = new Intent();
			intent.setType("image/*");
			// 由于Intent.ACTION_OPEN_DOCUMENT的版本是4.4以上的内容
			// 所以注意这个方法的最上面添加了@SuppressLint("InlinedApi")
			// 如果客户使用的不是4.4以上的版本，因为前面有判断，所以根本不会走else，
			// 也就不会出现任何因为这句代码引发的错误
			intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
			activity.startActivityForResult(intent, KITKAT_ABOVE);
		}
	}

	/**
	 * 下面几个方法来自于stackoverflow,虽然来自大神，但大神的代码也不是就那样？ 看不懂的地方挨个百度。
	 * -----------------------割------------------------- Get a file path from a
	 * Uri. This will get the the path for Storage Access Framework Documents,
	 * as well as the _data field for the MediaStore and other file-based
	 * ContentProviders.
	 *
	 * @param context
	 *            The context.
	 * @param uri
	 *            The Uri to query.
	 * @author paulburke
	 */
	@SuppressLint("NewApi")
	public String getPath(final Context context, final Uri uri) {

		final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

		// DocumentProvider
		if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
			// ExternalStorageProvider
			if (isExternalStorageDocument(uri)) {
				final String docId = DocumentsContract.getDocumentId(uri);
				final String[] split = docId.split(":");
				final String type = split[0];

				if ("primary".equalsIgnoreCase(type)) {
					return Environment.getExternalStorageDirectory() + "/"
							+ split[1];
				}

				// TODO handle non-primary volumes
			}
			// DownloadsProvider
			else if (isDownloadsDocument(uri)) {
				final String id = DocumentsContract.getDocumentId(uri);
				final Uri contentUri = ContentUris.withAppendedId(
						Uri.parse("content://downloads/public_downloads"),
						Long.valueOf(id));

				return getDataColumn(context, contentUri, null, null);
			}
			// MediaProvider
			else if (isMediaDocument(uri)) {
				final String docId = DocumentsContract.getDocumentId(uri);
				final String[] split = docId.split(":");
				final String type = split[0];

				Uri contentUri = null;
				if ("image".equals(type)) {
					contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
				} else if ("video".equals(type)) {
					contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
				} else if ("audio".equals(type)) {
					contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
				}

				final String selection = "_id=?";
				final String[] selectionArgs = new String[] { split[1] };

				return getDataColumn(context, contentUri, selection,
						selectionArgs);
			}
		}
		// MediaStore (and general)
		else if ("content".equalsIgnoreCase(uri.getScheme())) {

			// Return the remote address
			if (isGooglePhotosUri(uri))
				return uri.getLastPathSegment();

			return getDataColumn(context, uri, null, null);
		}
		// File
		else if ("file".equalsIgnoreCase(uri.getScheme())) {
			return uri.getPath();
		}

		return null;
	}

	/**
	 * Get the value of the data column for this Uri. This is useful for
	 * MediaStore Uris, and other file-based ContentProviders.
	 *
	 * @param context
	 *            The context.
	 * @param uri
	 *            The Uri to query.
	 * @param selection
	 *            (Optional) Filter used in the query.
	 * @param selectionArgs
	 *            (Optional) Selection arguments used in the query.
	 * @return The value of the _data column, which is typically a file path.
	 */
	public String getDataColumn(Context context, Uri uri, String selection,
								String[] selectionArgs) {

		Cursor cursor = null;
		final String column = "_data";
		final String[] projection = { column };

		try {
			cursor = context.getContentResolver().query(uri, projection,
					selection, selectionArgs, null);
			if (cursor != null && cursor.moveToFirst()) {
				final int index = cursor.getColumnIndexOrThrow(column);
				return cursor.getString(index);
			}
		} finally {
			if (cursor != null)
				cursor.close();
		}
		return null;
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is ExternalStorageProvider.
	 */
	public static boolean isExternalStorageDocument(Uri uri) {
		return "com.android.externalstorage.documents".equals(uri
				.getAuthority());
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is DownloadsProvider.
	 */
	public static boolean isDownloadsDocument(Uri uri) {
		return "com.android.providers.downloads.documents".equals(uri
				.getAuthority());
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is MediaProvider.
	 */
	public static boolean isMediaDocument(Uri uri) {
		return "com.android.providers.media.documents".equals(uri
				.getAuthority());
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is Google Photos.
	 */
	public static boolean isGooglePhotosUri(Uri uri) {
		return "com.google.android.apps.photos.content".equals(uri
				.getAuthority());
	}

	public String generateFileName(String type) {

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 6; i++) {
			sb.append(new Random().nextInt(10));
		}
		return sb.toString() + type;
	}

	public String generateFileUrl() {
		return "/attachment/";
	}

	public File createScreenFile(Context context) {

		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			// 已挂载
			File pic = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
					Locale.CHINA).format(new Date());
			String fileName = "multi_image_" + timeStamp + "";
			File tmpFile = new File(pic, fileName + ".jpg");
			return tmpFile;
		} else {
			File cacheDir = context.getCacheDir();
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
					Locale.CHINA).format(new Date());
			String fileName = "multi_image_" + timeStamp + "";
			File tmpFile = new File(cacheDir, fileName + ".jpg");
			return tmpFile;
		}

	}








}
