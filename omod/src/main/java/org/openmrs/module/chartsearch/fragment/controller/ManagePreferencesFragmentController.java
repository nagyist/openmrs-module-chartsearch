/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.chartsearch.fragment.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.openmrs.module.chartsearch.ChartSearchCache;
import org.openmrs.module.chartsearch.GeneratingJson;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.springframework.web.bind.annotation.RequestParam;

public class ManagePreferencesFragmentController {
	
	ChartSearchCache cache = new ChartSearchCache();
	
	public void controller(FragmentModel model, UiUtils ui) {
		model.put("preferences", GeneratingJson.generateRightMatchedPreferencesJSON().toString());
		model.put("daemonPreferences", GeneratingJson.generateDaemonPreferencesJSON().toString());
		model.put("categoryFilters", ui.escapeJs(GeneratingJson.generateAllCategoriesJSON().toString()));
	}
	
	public JSONObject saveOrUpdatePrefereces(@RequestParam("history") Boolean enableHistory,
	                                         @RequestParam("bookmarks") Boolean enableBookmarks,
	                                         @RequestParam("notes") Boolean enableNotes,
	                                         @RequestParam("quickSearches") Boolean enableQuickSearches,
	                                         @RequestParam("defaultSearch") Boolean enableDefaultSearch,
	                                         @RequestParam("duplicateResults") Boolean enableDuplicateResults,
	                                         @RequestParam("multiFiltering") Boolean enableMultiFiltering,
	                                         @RequestParam("categories[]") String[] cats) {
		
		return cache.saveOrUpdatePreferences(enableHistory, enableBookmarks, enableNotes, enableQuickSearches,
		    enableDefaultSearch, enableDuplicateResults, enableMultiFiltering, cats);
	}
	
	public JSONObject restoreDefaultPrefereces() {
		return cache.restorePreferences();
	}
}
