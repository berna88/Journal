package Journal.portlet;

import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.print.attribute.standard.JobOriginatingUserName;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.consistent.journal.util.JournalUtil;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.model.impl.JournalArticleImpl;
import com.liferay.journal.model.impl.JournalFolderImpl;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.journal.service.JournalArticleResourceLocalServiceUtil;
import com.liferay.journal.service.JournalFolderLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;


/**
 * @author liferay
 */
@Controller
@RequestMapping("VIEW")
public class JournalPortletViewController {
	private static final Log log = LogFactoryUtil.getLog(JournalPortletViewController.class);
	JournalUtil journal = new JournalUtil();

	@RenderMapping
	public String view(RenderRequest request, RenderResponse response) {
		
		log.info("Data: ");
		journal.getStructure("Hotels");
		return "view";
	}
	
	public long journalRootFolder(long parentFolder,String nameFolder){

		DynamicQuery query_journal_folder = DynamicQueryFactoryUtil.forClass(com.liferay.journal.model.impl.JournalFolderImpl.class, "journalFolder",PortalClassLoaderUtil.getClassLoader());
		Criterion criterion = null;
		criterion = RestrictionsFactoryUtil.eq("name", nameFolder);
		criterion = RestrictionsFactoryUtil.and(criterion, RestrictionsFactoryUtil.eq("parentFolderId", new Long(parentFolder)));
		query_journal_folder.add(criterion);
		List<com.liferay.journal.model.impl.JournalFolderImpl> ja_1 = JournalArticleResourceLocalServiceUtil.dynamicQuery(query_journal_folder);
		    for (com.liferay.journal.model.impl.JournalFolderImpl journalArticleResourceImpl : ja_1) {
		   	log.info("**********************Resource Journal folder****************");
		   	log.info(journalArticleResourceImpl.getName());
		   	log.info("**********************Resource Journal folder****************");
		}
		return ja_1.get(0).getFolderId();
		}
	
	
	public void getUser(){
		DynamicQuery userQuery = DynamicQueryFactoryUtil.forClass(User.class,
				PortalClassLoaderUtil.getClassLoader());
		userQuery.add(PropertyFactoryUtil.forName("lastName").eq("Test"));
		try {
			List<User> userList = UserLocalServiceUtil.dynamicQuery(userQuery);
			log.info("Ciclo");
			for (User user : userList) {
				
				log.info("user Id=>" + user.getUserId() + " Name=>" + user.getFirstName());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	

}