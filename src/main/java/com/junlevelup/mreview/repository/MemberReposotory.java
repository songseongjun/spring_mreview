package com.junlevelup.mreview.repository;

import com.junlevelup.mreview.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberReposotory  extends JpaRepository<Member,Long> {
}
