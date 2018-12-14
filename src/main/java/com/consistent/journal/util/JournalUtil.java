package com.consistent.journal.util;

import java.util.List;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.model.impl.JournalArticleImpl;
import com.liferay.journal.model.impl.JournalFolderImpl;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.journal.service.JournalFolderLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;

public class JournalUtil {
	private static final Log log = LogFactoryUtil.getLog(JournalUtil.class);

	//Metodo para obtener folder regresa el folderId
	public Long getFolder(String carpeta) {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(JournalFolderImpl.class, "folder",
				PortalClassLoaderUtil.getClassLoader());
		Long folderId = null;
		try {
			List<JournalFolder> folders = JournalFolderLocalServiceUtil.dynamicQuery(dynamicQuery);
			for (JournalFolder folder : folders) {
				if (folder.getName().equalsIgnoreCase(carpeta)) {
					folderId = folder.getFolderId();
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return folderId;
	}
	
	//Metodo para obtener estructura
	public String getStructure(String name) {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(JournalArticleImpl.class, "structure",
				PortalClassLoaderUtil.getClassLoader());
		// dynamicQuery.add(PropertyFactoryUtil.forName("structureId").eq("40684"));
		String structure = "";
		try {
			List<JournalArticle> articles = JournalArticleLocalServiceUtil.dynamicQuery(dynamicQuery);
			for (JournalArticle article : articles) {
				if (article.getFolderId() == getFolder("ratesr")) {
					structure = article.getArticleId();
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			// TODO: handle exception
		}
		return structure;
	}// Fin de metodo obtener structura
	
	//Metodo para obtener categorias
	public void getCategorory(){
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AssetCategory.class, "category", PortalClassLoaderUtil.getClassLoader());
		try {
			List<AssetCategory> assetCategories = AssetCategoryLocalServiceUtil.dynamicQuery(dynamicQuery);
			for(AssetCategory category : assetCategories){
				log.info(category.getName());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	

}// Fin de clase principal
