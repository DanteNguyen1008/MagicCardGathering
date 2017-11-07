package com.training.mcg.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * Created by An Nguyen on 9/18/2017.
 */

@Entity
public class Card {

	@SerializedName("id")
	@Expose
	public String globalId;
	@SerializedName("type")
	@Expose
	public String type;
	@Expose
	@Id
	long id;
	String name;
	String[] names;

	public String getId() {
		return "";
	}
}
