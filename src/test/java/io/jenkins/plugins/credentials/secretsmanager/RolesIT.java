package io.jenkins.plugins.credentials.secretsmanager;

import io.jenkins.plugins.casc.misc.ConfiguredWithCode;
import io.jenkins.plugins.credentials.secretsmanager.util.CreateSecretOperation;
import org.assertj.core.api.Assertions;
import org.jenkinsci.plugins.plaincredentials.StringCredentials;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RolesIT extends AbstractPluginIT {
    @Ignore("Moto does not support cross-account operations")
    public void shouldFetchCredentialsFromMultipleAccounts() {
        Assertions.fail("Implement this test");
    }

    @Ignore("Moto does not support cross-account operations")
    public void shouldThrowExceptionWhenDuplicateSecretNamesPresent() {
        Assertions.fail("Implement this test");
    }

    @Test
    @ConfiguredWithCode(value = "/invalid-roles.yml")
    public void shouldFailWhenRoleNotValid() {
        // Given
        final CreateSecretOperation.Result foo = createStringSecret("supersecret");

        // When
        final List<StringCredentials> credentials = lookupCredentials(StringCredentials.class);

        // Then
        assertThat(credentials)
                .isEmpty();
    }
}
