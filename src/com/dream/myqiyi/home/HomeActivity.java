package com.dream.myqiyi.home;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dream.myqiyi.R;
import com.dream.myqiyi.widget.FlowIndicator;

@SuppressLint("HandlerLeak")
public class HomeActivity extends Activity {
	static final int SCROLL_ACTION = 0;
	ExpandableListView mExpandableListView;
	int[] tags = new int[] { 0, 0, 0, 0, 0 };
//	String[] groups = new String[] { "同步剧场", "奇艺出品", "热播电影", "3月片花速递", "动漫乐园" };
//	String[][] childs = new String[5][10];
	Gallery mGallery;
	GalleryAdapter mGalleryAdapter;
	FlowIndicator mMyView;
	Timer mTimer;
	
	List<String> gruops;
	List<List<Map<String,Object>>> child;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_activity);
		prepareView();
		mTimer = new Timer();
		mTimer.scheduleAtFixedRate(new MyTask(), 0, 5000);
	}

	private void prepareView() {
		mExpandableListView = (ExpandableListView) findViewById(R.id.expandableListView1);
		gruops = new ArrayList<String>();  
		
		gruops.add("同步剧场");  
		gruops.add( "奇艺出品");  
        
        List<Map<String,Object>> content_1 = new ArrayList<Map<String,Object>>();
        Map<String,Object> map1=new HashMap<String,Object>();
        map1.put("childName","蜘蛛侠");
        map1.put("step", "20");
        content_1.add(map1);
        Map<String,Object> map2=new HashMap<String,Object>();
        map2.put("childName", "钢铁侠");
        map2.put("step","30");
        content_1.add(map2); 
        
        List<Map<String,Object>> content_2 = new ArrayList<Map<String,Object>>();  
        Map<String,Object> map3=new HashMap<String,Object>();
        map3.put("childName","金粉世家");
        map3.put("step", "25");
        content_2.add(map3);
        Map<String,Object> map4=new HashMap<String,Object>();
        map4.put("childName","西游记");
        map4.put("step","34");
        content_2.add(map4); 
        Map<String,Object> map5=new HashMap<String,Object>();
        map5.put("childName","水浒传");
        map5.put("step","50");
        content_2.add(map5); 
        
        child=new ArrayList<List<Map<String,Object>>>();
        child.add(content_1);
        child.add(content_2);


		MyListAdapter adapter = new MyListAdapter();
		View header = LayoutInflater.from(this).inflate(R.layout.header_view,
				null);
		mGallery = (Gallery) header.findViewById(R.id.home_gallery);
		mMyView = (FlowIndicator) header.findViewById(R.id.myView);
		mGalleryAdapter = new GalleryAdapter(this);
		mMyView.setCount(mGalleryAdapter.getCount());
		mGallery.setAdapter(mGalleryAdapter);
		mGallery.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				mMyView.setSeletion(arg2);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
		mExpandableListView.addHeaderView(header);
		mExpandableListView.setAdapter(adapter);
		mExpandableListView
				.setOnGroupExpandListener(new OnGroupExpandListener() {
					
					@Override
					public void onGroupExpand(int arg0) {
						// TODO Auto-generated method stub
						tags[arg0] = 1;
					}
				});
		mExpandableListView
				.setOnGroupCollapseListener(new OnGroupCollapseListener() {

					@Override
					public void onGroupCollapse(int arg0) {
						// TODO Auto-generated method stub
						tags[arg0] = 0;
					}
				});
//		mExpandableListView.setOnLongClickListener(new OnLongClickListener(){
//
//			@Override
//			public boolean onLongClick(View v) {
//				 Intent intent=new Intent();
//				   intent.setClass(HomeActivity.this,ChildActivity.class);
//				   startActivity(intent);
//				return false;
//			}
//			
//		});
		mExpandableListView.setOnChildClickListener(new OnChildClickListener() {
			   @Override
				  public boolean onChildClick(ExpandableListView parent, View v,
					  int groupPosition, int childPosition, long id) {
					   Intent intent=new Intent();
					   intent.setClass(HomeActivity.this,ChildActivity.class);
					   startActivity(intent);
				   return true;
				  }
				 });
	}

	private class MyTask extends TimerTask {
		@Override
		public void run() {
			mHandler.sendEmptyMessage(SCROLL_ACTION);
		}
	}

	@SuppressLint("HandlerLeak")
	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case SCROLL_ACTION:
				MotionEvent e1 = MotionEvent.obtain(SystemClock.uptimeMillis(),
						SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN,
						89.333336f, 265.33334f, 0);
				MotionEvent e2 = MotionEvent.obtain(SystemClock.uptimeMillis(),
						SystemClock.uptimeMillis(), MotionEvent.ACTION_UP,
						300.0f, 238.00003f, 0);

				mGallery.onFling(e1, e2, -1300, 0);
				break;

			default:
				break;
			}
		}
	};


	class MyListAdapter extends BaseExpandableListAdapter {
		
		@Override  
        public Object getChild(int groupPosition, int childPosition) {  
            return child.get(groupPosition).get(childPosition);  
        }  
  
        @Override  
        public long getChildId(int groupPosition, int childPosition) {  
            return 0;  
        }  
  
        @SuppressWarnings("static-access")
		@Override  
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {  
        	LinearLayout layout = (LinearLayout) parent.inflate(getApplicationContext(), R.layout.list_child_item,null);
        	TextView textView1=(TextView) layout.findViewById(R.id.textView1);
        	TextView textView2=(TextView) layout.findViewById(R.id.textView5);
            if (convertView != null) {  
            	textView1.setText((String)child.get(groupPosition).get(childPosition).get("childName"));  
            	textView2.setText((String)child.get(groupPosition).get(childPosition).get("step")); 
            } else {  
            	textView1 = createView((String)child.get(groupPosition).get(childPosition).get("childName"));  
            	textView2 = createView((String)child.get(groupPosition).get(childPosition).get("step"));  
            }  
            return layout;  
        }  
  
        @Override  
        public int getChildrenCount(int groupPosition) {  
            return child.get(groupPosition).size();  
        }  
  
        @Override  
        public Object getGroup(int groupPosition) {  
            return gruops.get(groupPosition);  
        }  
  
        @Override  
        public int getGroupCount() {  
            return gruops.size();  
        }  
  
        @Override  
        public long getGroupId(int groupPosition) {  
            return 0;  
        }  
	        
		
		class GroupHolder {
			ImageView img;
			TextView title;
		}
		
		@Override
		public View getGroupView(int arg0, boolean arg1, View arg2,
				ViewGroup arg3) {
			// TODO Auto-generated method stub
			GroupHolder groupHolder;
			if (arg2 == null) {
				arg2 = LayoutInflater.from(HomeActivity.this).inflate(
						R.layout.list_group_item, null);
				groupHolder = new GroupHolder();
				groupHolder.img = (ImageView) arg2.findViewById(R.id.tag_img);
				groupHolder.title = (TextView) arg2
						.findViewById(R.id.title_view);
				arg2.setTag(groupHolder);
			} else {
				groupHolder = (GroupHolder) arg2.getTag();
			}
			if (tags[arg0] == 0) {
				groupHolder.img
				.setImageResource(R.drawable.list_indecator_button);
			} else {
				groupHolder.img
				.setImageResource(R.drawable.list_indecator_button_down);
			}
			groupHolder.title.setText(gruops.get(arg0));
			
			return arg2;
		}
		
		@Override
		public boolean hasStableIds() {
			// TODO Auto-generated method stub
			return true;
		}
		
		@Override
		public boolean isChildSelectable(int arg0, int arg1) {
			return true;
		}
		private TextView createView(String content) {  
            AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(    
                    ViewGroup.LayoutParams.FILL_PARENT, 38);    
            TextView text = new TextView(HomeActivity.this);    
            text.setLayoutParams(layoutParams);    
            text.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);    
            text.setPadding(40, 0, 0, 0);    
            text.setText(content);  
            return text;  
        }  
		
	}
}
