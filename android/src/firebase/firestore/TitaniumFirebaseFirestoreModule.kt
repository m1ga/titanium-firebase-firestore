/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * TiDev Titanium Mobile
 * Copyright TiDev, Inc. 04/07/2022-Present
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */

package firebase.firestore

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.appcelerator.kroll.KrollDict
import org.appcelerator.kroll.KrollFunction
import org.appcelerator.kroll.KrollModule
import org.appcelerator.kroll.annotations.Kroll
import org.appcelerator.titanium.util.TiConvert


@Kroll.module(name = "TitaniumFirebaseFirestore", id = "firebase.firestore")
class TitaniumFirebaseFirestoreModule: KrollModule() {

	// Methods

	@Kroll.method
	fun addDocument(params: KrollDict) {
		val callback = params["callback"] as KrollFunction
		val collection = params["collection"] as String
		var document = ""
		if (params.containsKeyAndNotNull("document")) {
			document = params["document"] as String
		}
		val data = params.getKrollDict("data")
		if (document.isEmpty()) {
			// auto-id document
			Firebase.firestore.collection(collection)
					.add(data)
					.addOnSuccessListener {
						val event = KrollDict()
						event["success"] = true
						event["documentID"] = it.id

						callback.callAsync(getKrollObject(), event)
					}
					.addOnFailureListener { error ->
						val event = KrollDict()
						event["success"] = false
						event["error"] = error.localizedMessage

						callback.callAsync(getKrollObject(), event)
					}
		} else {
			// fixed document
			Firebase.firestore.collection(collection)
					.document(document)
					.set(data)
					.addOnSuccessListener{
						val event = KrollDict()
						event["success"] = true
						event["documentID"] = document

						callback.callAsync(getKrollObject(), event)
					}
					.addOnFailureListener { error ->
						val event = KrollDict()
						event["success"] = false
						event["error"] = error.localizedMessage

						callback.callAsync(getKrollObject(), event)
					}
		}
	}

	@Kroll.method
	fun getDocuments(params: KrollDict) {
		val callback = params["callback"] as KrollFunction
		val collection = params["collection"] as String

		Firebase.firestore.collection(collection)
			.get()
			.addOnSuccessListener { it ->

				val list = mutableListOf<Map<String, Any>>()
				for (document in it.documents) {
					val d = KrollDict(document.data)
					d["_id"] = document.id
					list.add(d)
				}

				val event = KrollDict()
				event["success"] = true
				event["documents"] = list.toTypedArray()

				callback.callAsync(getKrollObject(), event)
			}
			.addOnFailureListener { error ->
				val event = KrollDict()
				event["success"] = false
				event["error"] = error.localizedMessage

				callback.callAsync(getKrollObject(), event)
			}
	}
	@Kroll.method
	fun getDocument(params: KrollDict) {
		val callback = params["callback"] as KrollFunction
		val collection = params["collection"] as String
		val document = TiConvert.toString(params["document"],"")
		if (document.isEmpty()){
			return
		}
		val docRef = Firebase.firestore.collection(collection).document(document)
		docRef.get()
			.addOnSuccessListener { it ->

				val event = KrollDict()
				if (it != null && it.data != null) {
					val d = KrollDict(it.data)
					d["_id"] = it.id
					event["document"] = d
				} else {
					event["document"] = "";
				}

				event["success"] = true

				callback.callAsync(getKrollObject(), event)
			}
			.addOnFailureListener { error ->
				val event = KrollDict()
				event["success"] = false
				event["error"] = error.localizedMessage

				callback.callAsync(getKrollObject(), event)
			}
	}

	@Kroll.method
	fun updateDocument(params: KrollDict) {
		val callback = params["callback"] as KrollFunction
		val collection = params["collection"] as String
		val data = params.getKrollDict("data")
		val document = params["document"] as String

		Firebase.firestore.collection(collection)
			.document(document)
			.update(data)
			.addOnSuccessListener {
				val event = KrollDict()
				event["success"] = true

				callback.callAsync(getKrollObject(), event)
			}
			.addOnFailureListener { error ->
				val event = KrollDict()
				event["success"] = false
				event["error"] = error.localizedMessage

				callback.callAsync(getKrollObject(), event)
			}
	}

	@Kroll.method
	fun deleteDocument(params: KrollDict) {
		val callback = params["callback"] as KrollFunction
		val collection = params["collection"] as String
		val document = params["document"] as String

		Firebase.firestore.collection(collection)
			.document(document)
			.delete()
			.addOnSuccessListener {
				val event = KrollDict()
				event["success"] = true

				callback.callAsync(getKrollObject(), event)
			}
			.addOnFailureListener { error ->
				val event = KrollDict()
				event["success"] = false
				event["error"] = error.localizedMessage

				callback.callAsync(getKrollObject(), event)
			}
	}
}
