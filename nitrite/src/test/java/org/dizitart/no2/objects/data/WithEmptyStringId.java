package org.dizitart.no2.objects.data;

import lombok.Getter;
import lombok.Setter;
import org.dizitart.no2.objects.Id;

/**
 * @author Anindya Chatterjee.
 */
@Getter @Setter
public class WithEmptyStringId {
    @Id
    private String name;
}
