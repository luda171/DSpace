/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * https://github.com/CILEA/dspace-cris/wiki/License
 */
package org.dspace.app.cris.dao;

import it.cilea.osd.jdyna.dao.TabDao;

import org.dspace.app.cris.model.jdyna.BoxProject;
import org.dspace.app.cris.model.jdyna.ProjectPropertiesDefinition;
import org.dspace.app.cris.model.jdyna.TabProject;



public interface ProjectTabDao extends TabDao<BoxProject,TabProject,ProjectPropertiesDefinition> {

}
