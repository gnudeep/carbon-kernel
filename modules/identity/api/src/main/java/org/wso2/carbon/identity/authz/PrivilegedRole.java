/*
 *  Copyright (c) 2005-2013, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */

package org.wso2.carbon.identity.authz;

import java.util.Collections;
import java.util.List;

import org.wso2.carbon.identity.authn.PrivilegedGroup;
import org.wso2.carbon.identity.authn.PrivilegedUser;
import org.wso2.carbon.identity.authn.StoreIdentifier;
import org.wso2.carbon.identity.authn.spi.GroupSearchCriteria;
import org.wso2.carbon.identity.authn.spi.UserSearchCriteria;
import org.wso2.carbon.identity.authz.spi.AuthorizationStore;
import org.wso2.carbon.identity.commons.EntityTree;
import org.wso2.carbon.identity.commons.EntryIdentifier;

public class PrivilegedRole extends Role {

	private AuthorizationStore authzStore;
	private EntryIdentifier entryId;

	/**
	 * 
	 * @param authzStore
	 * @param roleIdentifier
	 */
	public PrivilegedRole(AuthorizationStore authzStore,
			RoleIdentifier roleIdentifier) {
		super(roleIdentifier);
		this.authzStore = authzStore;
	}

	/**
	 * 
	 * @return
	 */
	public EntryIdentifier getEntryId() {
		return entryId;
	}

	/**
	 * 
	 * @return
	 * @throws AuthorizationStoreException
	 */
	public List<Permission> getPermissions() throws AuthorizationStoreException {
		List<Permission> permission = authzStore
				.getPermissions(getRoleIdentifier());
		return Collections.unmodifiableList(permission);
	}

	/**
	 * 
	 * @param permission
	 * @throws AuthorizationStoreException
	 */
	public void addPermission(List<Permission> permission)
			throws AuthorizationStoreException {
		authzStore.addPermissionToRole(getRoleIdentifier(), permission);
	}

	/**
	 * 
	 * @param permission
	 * @throws AuthorizationStoreException
	 */
	public void removePermission(List<Permission> permission)
			throws AuthorizationStoreException {
		authzStore.removePermissionFromRole(getRoleIdentifier(), permission);
	}

	/**
	 * 
	 * @return
	 * @throws AuthorizationStoreException
	 */
	public List<PrivilegedGroup> getGroups() throws AuthorizationStoreException {
		return authzStore.getGroupsOfRole(getRoleIdentifier());
	}

	/**
	 * 
	 * @param searchCriteria
	 * @return
	 * @throws AuthorizationStoreException
	 */
	public List<PrivilegedGroup> getGroups(GroupSearchCriteria searchCriteria)
			throws AuthorizationStoreException {
		List<PrivilegedGroup> groups = authzStore.getGroupsOfRole(
				getRoleIdentifier(), searchCriteria);
		return Collections.unmodifiableList(groups);
	}

	/**
	 * 
	 * @return
	 * @throws AuthorizationStoreException
	 */
	public List<PrivilegedUser> getUsers() throws AuthorizationStoreException {
		List<PrivilegedUser> users = authzStore
				.getUsersOfRole(getRoleIdentifier());
		return Collections.unmodifiableList(users);
	}

	/**
	 * 
	 * @param searchCriteria
	 * @return
	 * @throws AuthorizationStoreException
	 */
	public List<PrivilegedUser> getUsers(UserSearchCriteria searchCriteria)
			throws AuthorizationStoreException {
		List<PrivilegedUser> users = authzStore.getUsersOfRole(
				getRoleIdentifier(), searchCriteria);
		return Collections.unmodifiableList(users);
	}

	/**
	 * 
	 * @return
	 * @throws AuthorizationStoreException
	 */
	public List<EntityTree> getChildren() throws AuthorizationStoreException {
		List<EntityTree> children = authzStore.getChildren(getRoleIdentifier());
		return Collections.unmodifiableList(children);
	}

	/**
	 * 
	 * @param childRoleIdentifier
	 * @return
	 * @throws AuthorizationStoreException
	 */
	public boolean hasChild(RoleIdentifier childRoleIdentifier)
			throws AuthorizationStoreException {
		return authzStore.hasChild(getRoleIdentifier(), childRoleIdentifier);
	}

	/**
	 * 
	 * @param parentRoleIdentifier
	 * @return
	 * @throws AuthorizationStoreException
	 */
	public boolean hasParent(RoleIdentifier parentRoleIdentifier)
			throws AuthorizationStoreException {
		return authzStore.hasParent(getRoleIdentifier(), parentRoleIdentifier);
	}

	/**
	 * 
	 * @return
	 */
	public StoreIdentifier getStoreIdentifier() {
		return authzStore.getStoreIdentifier();
	}

}