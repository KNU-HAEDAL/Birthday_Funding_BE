package team.haedal.gifticionfunding.service;

import org.springframework.stereotype.Service;
import team.haedal.gifticionfunding.dto.response.GiftiConResponse;

import java.util.ArrayList;
import java.util.List;

@Service
public class GifticonServiceImpl implements GifticonService{
    public List<GiftiConResponse> getGifticons(){
        List<GiftiConResponse> giftiConResponseList = new ArrayList<>();
        return giftiConResponseList;
    }
}
