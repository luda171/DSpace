/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */
package org.dspace.app.webui.components.signposting;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.dspace.app.webui.util.UIUtil;
import org.dspace.authorize.AuthorizeException;
import org.dspace.content.Bitstream;
import org.dspace.content.DSpaceObject;
import org.dspace.core.Constants;
import org.dspace.core.Context;
import org.dspace.plugin.PluginException;
import org.dspace.plugin.signposting.BitstreamSignPostingProcessor;

/**
 * @author Pascarelli Luigi Andrea
 */
public class IdentifierBitstreamHome
        implements BitstreamSignPostingProcessor
{

    /** log4j category */
    private static Logger log = Logger
            .getLogger(IdentifierBitstreamHome.class);

    private String relation = "identifier";
    private String metadataField;
    private String pattern;

    @Override
    public void process(Context context, HttpServletRequest request,
            HttpServletResponse response, Bitstream bitstream)
            throws PluginException, AuthorizeException
    {

        try
        {
            DSpaceObject dso = bitstream.getParentObject();
            if (dso != null)
            {
                String metadata = dso.getMetadata(getMetadataField());
                String value = UIUtil.encodeBitstreamName(metadata,
                        Constants.DEFAULT_ENCODING);
                if(StringUtils.isNotBlank(pattern)) {
                    response.addHeader("Link", MessageFormat.format(getPattern(), value) + "; rel=\"" + getRelation()
                        + "\"");
                }
                else {
                    response.addHeader("Link", value + "; rel=\"" + getRelation()
                    + "\"");
                }
            }
        }
        catch (SQLException | UnsupportedEncodingException e)
        {
            throw new PluginException(e);
        }
    }

    public String getRelation()
    {
        return relation;
    }

    public void setRelation(String relation)
    {
        this.relation = relation;
    }

    public String getMetadataField()
    {
        return metadataField;
    }

    public void setMetadataField(String metadataField)
    {
        this.metadataField = metadataField;
    }

    public String getPattern()
    {
        return pattern;
    }

    public void setPattern(String pattern)
    {
        this.pattern = pattern;
    }

}
