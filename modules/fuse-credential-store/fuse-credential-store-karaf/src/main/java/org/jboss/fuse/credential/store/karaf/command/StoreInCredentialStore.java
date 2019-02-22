/**
 *  Copyright 2005-2018 Red Hat, Inc.
 *
 *  Red Hat licenses this file to you under the Apache License, version
 *  2.0 (the "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.  See the License for the specific language governing
 *  permissions and limitations under the License.
 */
package org.jboss.fuse.credential.store.karaf.command;

import org.apache.karaf.shell.api.action.Argument;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.lifecycle.Service;
import org.jboss.fuse.credential.store.karaf.Activator;
import org.jboss.fuse.credential.store.karaf.util.CredentialStoreHelper;
import org.wildfly.security.credential.Credential;
import org.wildfly.security.credential.PasswordCredential;
import org.wildfly.security.credential.store.CredentialStore;
import org.wildfly.security.password.Password;
import org.wildfly.security.password.PasswordFactory;
import org.wildfly.security.password.interfaces.ClearPassword;
import org.wildfly.security.password.spec.ClearPasswordSpec;

/**
 * Places a secret value in the Credential store under the specified alias configured by the environment variables.
 */
@Command(scope = "credential-store", name = "store", description = "Store secret in the credential store")
@Service
public class StoreInCredentialStore extends AbstractCredentialStoreCommand {

    @Argument(index = 0, required = true, description = "Alias for credential Store entry")
    String alias;

    @Argument(index = 1, required = true, description = "Secret value to put into Credential Store")
    String secret;

    @Override
    public Object execute() throws Exception {
        if (!validate()) {
            return null;
        }

        final CredentialStore credentialStore = Activator.credentialStore;

        if (credentialStore.exists(alias, Credential.class)) {
            System.out.println("Entry with alias \"" + alias + "\" already exists in credential store.");
            return null;
        }

        final PasswordFactory passwordFactory = PasswordFactory.getInstance(ClearPassword.ALGORITHM_CLEAR,
                Activator.getElytronProvider());
        final Password password = passwordFactory.generatePassword(new ClearPasswordSpec(secret.toCharArray()));

        credentialStore.store(alias, new PasswordCredential(password));
        credentialStore.flush();

        System.out.println("Value stored in the credential store. To reference it use: "
            + CredentialStoreHelper.referenceForAlias(alias));

        return null;
    }

}
