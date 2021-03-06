/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */
package org.dspace.app.xmlui.aspect.administrative.batchimport;

import java.sql.SQLException;

import org.apache.cocoon.environment.ObjectModelHelper;
import org.apache.cocoon.environment.Request;

import org.dspace.app.xmlui.cocoon.AbstractDSpaceTransformer;
import org.dspace.app.xmlui.wing.Message;
import org.dspace.app.xmlui.wing.WingException;
import org.dspace.app.xmlui.wing.element.Body;
import org.dspace.app.xmlui.wing.element.Button;
import org.dspace.app.xmlui.wing.element.Division;
import org.dspace.app.xmlui.wing.element.PageMeta;
import org.dspace.app.xmlui.wing.element.Para;
import org.xml.sax.SAXException;


/**
 * Web interface to Batch Import app.
 * Display summary of committed changes
 *
 * @author Peter Dietz
 */

public class BatchImportConfirm extends AbstractDSpaceTransformer {

    /** Language strings */
    private static final Message T_dspace_home = message("xmlui.general.dspace_home");
    private static final Message T_submit_return = message("xmlui.general.return");
    private static final Message T_trail = message("xmlui.administrative.metadataimport.general.trail");
    private static final Message T_changes = message("xmlui.administrative.metadataimport.general.changes");
    private static final Message T_title = message("xmlui.administrative.metadataimport.general.title");
    private static final Message T_head1 = message("xmlui.administrative.metadataimport.general.head1");

    private static final Message T_success = message("xmlui.administrative.metadataimport.MetadataImportConfirm.success");

    @Override
    public void addPageMeta(PageMeta pageMeta) throws WingException
    {
        pageMeta.addMetadata("title").addContent(T_title);

        pageMeta.addTrailLink(contextPath + "/", T_dspace_home);
        pageMeta.addTrail().addContent(T_trail);
    }


    @Override
    public void addBody(Body body) throws SAXException, WingException, SQLException
    {
        // Get list of changes

        Request request = ObjectModelHelper.getRequest(objectModel);

        // DIVISION: metadata-import
        Division div = body.addInteractiveDivision("batch-import",contextPath + "/admin/batchimport", Division.METHOD_MULTIPART,"primary administrative");
        div.setHead(T_head1);
        Para para = div.addPara();
        para.addContent(T_success);

        para.addContent(T_changes);


        Para actions = div.addPara();
        Button cancel = actions.addButton("submit_return");
        cancel.setValue(T_submit_return);


        div.addHidden("administrative-continue").setValue(knot.getId());
    }



}
