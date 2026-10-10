package org.example.sb_webui_crud.dto;

public record MovieResponse(Long id, String title, String director, String distributor, float score,
                            boolean isAdultOnly) {
}
