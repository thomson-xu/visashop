package com.tms.services.front.favorite;import com.tms.core.Services;import com.tms.services.front.favorite.bean.Favorite;public interface FavoriteService extends Services<Favorite> {	int selectCount(Favorite favorite);}