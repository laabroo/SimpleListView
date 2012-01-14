package com.laabroo.android.s4s;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.ListActivity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.laabroo.android.s4s.util.ConvertXml;

public class MainActivity extends ListActivity {

	private String param;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listmusic);

		param = "anggun";
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String dataXMl = ConvertXml.getXML(param);
		Document document = ConvertXml.XmlFromString(dataXMl);

		NodeList nodeList = document.getElementsByTagName("file");
		for (int i = 0; i < nodeList.getLength(); i++) {
			HashMap<String, String> hashMap = new HashMap<String, String>();
			Element element = (Element) nodeList.item(i);
			// hashMap.put("id", ConvertXml.getValue(element, "id"));
			hashMap.put("name", ConvertXml.getValue(element, "name"));
			hashMap.put("upload-date",
					"Update : " + ConvertXml.getValue(element, "upload-date"));
			hashMap.put("size",
					"Size : " + ConvertXml.getValue(element, "size"));
			hashMap.put("flash-preview-url",
					ConvertXml.getValue(element, "flash-preview-url"));
			list.add(hashMap);
		}

		ListAdapter adapter = new SimpleAdapter(this, list, R.layout.main,
				new String[] { "name", "upload-date", "size" }, new int[] {
						R.id.item_title, R.id.item_update, R.id.item_size });
		setListAdapter(adapter);

		final ListView listView = getListView();
		listView.setTextFilterEnabled(true);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				@SuppressWarnings("unchecked")
				HashMap<String, String> obj = (HashMap<String, String>) listView
						.getItemAtPosition(position);
				String url = new String(obj.get("flash-preview-url"));
				playMusic(url);
				Toast.makeText(MainActivity.this, "Url preview '" + url,
						Toast.LENGTH_LONG).show();
			}

		});

	}

	private void playMusic(String url) {
		MediaPlayer mediaPlayer = new MediaPlayer();
		try {
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setDataSource(url);
			mediaPlayer.prepare();

			if (mediaPlayer.isPlaying()) {
				mediaPlayer.stop();
			} else {
				mediaPlayer.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}