package com.laabroo.android.s4s;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.view.View.OnClickListener;

import com.laabroo.android.s4s.util.ConvertXml;

public class MainActivity extends ListActivity {
	private static final String TAG = "MainActivity";
	private EditText editText;
	private Button btnSearch;
	private String param;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listmusic);

		editText = (EditText) findViewById(R.id.textJudul);
		btnSearch = (Button) findViewById(R.id.btnSearch);
		btnSearch.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				param = editText.getText().toString();
				if (param.equals("")) {
					Toast.makeText(MainActivity.this,
							"Title Canot be null" + param, Toast.LENGTH_LONG)
							.show();
				} else {
					getMusic(param);
				}
			}
		});
	}

	public void getMusic(String url) {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String dataXMl = ConvertXml.getXML(url);
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
				Toast.makeText(MainActivity.this, "Url preview '" + url,
						Toast.LENGTH_LONG).show();
			}

		});

	}

}