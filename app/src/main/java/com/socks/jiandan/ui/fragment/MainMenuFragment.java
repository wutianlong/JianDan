package com.socks.jiandan.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.socks.jiandan.R;
import com.socks.jiandan.base.BaseFragment;
import com.socks.jiandan.model.MenuItem;
import com.socks.jiandan.ui.MainActivity;
import com.socks.jiandan.utils.ShowToast;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by zhaokaiqiang on 15/4/9.
 */
public class MainMenuFragment extends BaseFragment {

	@InjectView(R.id.recycler_view)
	RecyclerView mRecyclerView;
	@InjectView(R.id.rl_container)
	RelativeLayout rl_container;

	private LinearLayoutManager mLayoutManager;
	private MainActivity mainActivity;
	private MenuAdapter mAdapter;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		if (activity instanceof MainActivity) {
			mainActivity = (MainActivity) activity;
		} else {
			throw new IllegalArgumentException("The activity must be a MainActivity !");
		}

	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_drawer, container, false);
		ButterKnife.inject(this, view);

		mLayoutManager = new LinearLayoutManager(getActivity());
		mRecyclerView.setLayoutManager(mLayoutManager);

		rl_container.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ShowToast.Short("设置");
			}
		});

		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		mAdapter = new MenuAdapter();
		mAdapter.menuItems.add(new MenuItem("新鲜事", R.drawable.ic_explore_white_24dp, JokeFragment.class));
		mAdapter.menuItems.add(new MenuItem("无聊图", R.drawable.ic_mood_white_24dp, PictureFragment.class));
		mAdapter.menuItems.add(new MenuItem("妹子图", R.drawable.ic_local_florist_white_24dp, JokeFragment
				.class));
		mAdapter.menuItems.add(new MenuItem("段子", R.drawable.ic_chat_white_24dp, JokeFragment.class));
		mAdapter.menuItems.add(new MenuItem("小电影", R.drawable.ic_movie_white_24dp, JokeFragment.class));

		mRecyclerView.setAdapter(mAdapter);


	}

	private class MenuAdapter extends RecyclerView.Adapter<ViewHolder> {

		private ArrayList<MenuItem> menuItems;

		public MenuAdapter() {
			menuItems = new ArrayList<MenuItem>();
		}

		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_item,
					parent, false);
			return new ViewHolder(view);
		}

		@Override
		public void onBindViewHolder(ViewHolder holder, int position) {
			final MenuItem menuItem = menuItems.get(position);
			holder.tv_title.setText(menuItem.getTitle());
			holder.img_menu.setImageResource(menuItem.getResourceId());
			holder.rl_container.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {

					try {
						Fragment fragment = (Fragment) Class.forName(menuItem.getFragment()
								.getName()).newInstance();
						mainActivity.replaceFragment(R.id.frame_container, fragment);
					} catch (Exception e) {
						e.printStackTrace();
					}

					mainActivity.closeDrawer();

				}
			});
		}

		@Override
		public int getItemCount() {
			return menuItems.size();
		}
	}

	private static class ViewHolder extends RecyclerView.ViewHolder {

		private ImageView img_menu;
		private TextView tv_title;
		private RelativeLayout rl_container;


		public ViewHolder(View itemView) {
			super(itemView);
			img_menu = (ImageView) itemView.findViewById(R.id.img_menu);
			tv_title = (TextView) itemView.findViewById(R.id.tv_title);
			rl_container = (RelativeLayout) itemView.findViewById(R.id.rl_container);
		}
	}

}
