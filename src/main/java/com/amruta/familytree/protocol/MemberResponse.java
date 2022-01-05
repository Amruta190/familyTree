package com.amruta.familytree.protocol;

public class MemberResponse
{
    public MemberResponse()
    {
    }

    public Long getMemberId()
    {
        return memberId;
    }

    public void setMemberId(Long memberId)
    {
        this.memberId = memberId;
    }

    private Long memberId;
}
