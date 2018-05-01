/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.misyn.codequality.dto;

import com.misyn.codequality.entity.BaseEntity;

public abstract class BaseDto<E extends BaseEntity> {

    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public abstract E toEntity();
}
