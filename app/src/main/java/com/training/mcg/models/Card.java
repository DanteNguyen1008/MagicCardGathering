package com.training.mcg.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by An Nguyen on 9/18/2017.
 */
@Entity
public class Card {

	/**
	 * The Global id.
	 */
	@ColumnInfo(name = "global_id")
	@SerializedName("id")
	@Expose
	public String globalId;

	/**
	 * The Type.
	 */
	@ColumnInfo(name = "type")
	@SerializedName("type")
	@Expose
	public String type;

	/**
	 * The Primary id.
	 */
	@Expose
	@PrimaryKey
	@ColumnInfo(name = "primary_id")
	long primaryId;

	/**
	 * The Name.
	 */
	@ColumnInfo(name = "name")
	String name;

	/**
	 * Instantiates a new Card.
	 */
	public Card() {

	}

	/**
	 * Instantiates a new Card.
	 *
	 * @param globalId the global id
	 * @param type     the type
	 * @param name     the name
	 */
	public Card(String globalId, String type, String name) {
		this.globalId = globalId;
		this.type = type;
		this.name = name;
	}

	/**
	 * Gets global id id.
	 *
	 * @return the id
	 */
	public String getId() {
		return this.globalId;
	}
}
