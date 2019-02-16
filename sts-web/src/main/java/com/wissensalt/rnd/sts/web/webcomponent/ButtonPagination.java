package com.wissensalt.rnd.sts.web.webcomponent;

import com.wissensalt.rnd.sts.shared.data.dto.response.ResponsePaginationDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2/12/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ButtonPagination implements Serializable {
    private String className;
    private String iconName;
    private String link;
    private String text;

    public static ButtonPagination getStartFirstButton() {
        return new ButtonPagination("paginate_button previous disabled", "fa fa-angle-double-left", "#", "");
    }

    public static ButtonPagination getChangedFirstButton(String p_PaginationUrl, int p_Size) {
        return new ButtonPagination("paginate_button previous ", "fa fa-angle-double-left", p_PaginationUrl+"?offset=0&size="+p_Size, "");
    }

    public static ButtonPagination getStartPreviousButton() {
        return new ButtonPagination("paginate_button previous disabled", "fa fa-angle-left", "#" , "");
    }

    public static ButtonPagination getChangedPrevButton(int p_PrevValue, String p_PaginationUrl, int p_Size) {
        return new ButtonPagination("paginate_button previous ", "fa fa-angle-left", p_PaginationUrl+"?offset="+p_PrevValue+"&size="+p_Size, "");
    }

    public static ButtonPagination getNextButton(int p_NextValue, boolean last, String p_PaginationUrl, int p_Size) {
        if (last) {
            return new ButtonPagination("paginate_button disabled", "fa fa-angle-right", "#", "");
        }else {
            return new ButtonPagination("paginate_button ", "fa fa-angle-right", p_PaginationUrl+"?offset="+(p_NextValue)+"&size="+p_Size, "");
        }
    }

    public static ButtonPagination getLastButton(int p_LastValue, boolean last, String p_PaginationUrl, int p_Size) {
        if (last) {
            return new ButtonPagination("paginate_button next disabled", "fa fa-angle-double-right", "#" , "");
        }else {
            return new ButtonPagination("paginate_button next ", "fa fa-angle-double-right", p_PaginationUrl+"?offset="+(p_LastValue)+"&size="+p_Size , "");
        }
    }


    public static ButtonPagination getIndexedButton(int p_Index, int p_Number, String p_PaginationUrl, int p_Size) {
        if ((p_Index) == p_Number){
            return new ButtonPagination("paginate_button active", null, p_PaginationUrl+"?offset="+(p_Index)+"&size="+p_Size, String.valueOf((p_Index+1)));
        }else {
            return new ButtonPagination("paginate_button", null, p_PaginationUrl+"?offset="+(p_Index)+"&size="+p_Size, String.valueOf((p_Index+1)));
        }

    }

    public static List<ButtonPagination> buildStartPagination(ResponsePaginationDTO p_ResponsePage, String p_PaginationUrl) {
        List<ButtonPagination> buttonPaginations = new ArrayList<>();
        buttonPaginations.add(getStartFirstButton());
        buttonPaginations.add(getStartPreviousButton());
        if (p_ResponsePage.getTotalPages()<5 && p_ResponsePage.getTotalPages() > 0) {
            for (int a=0; a<p_ResponsePage.getTotalPages(); a++) {
                buttonPaginations.add(getIndexedButton(a, p_ResponsePage.getNumber(), p_PaginationUrl, 5));
            }
        }else {
            for (int a=0; a<5; a++) {
                buttonPaginations.add(getIndexedButton(a, p_ResponsePage.getNumber(), p_PaginationUrl, 5));
            }
        }
        if (p_ResponsePage.isLast()) {
            buttonPaginations.add(getNextButton(p_ResponsePage.getNumber() + 1, true, p_PaginationUrl, 5));
            buttonPaginations.add(getLastButton(p_ResponsePage.getTotalPages(), true, p_PaginationUrl, 5));
        }else {
            buttonPaginations.add(getNextButton(p_ResponsePage.getNumber() + 1, false, p_PaginationUrl, 5));
            buttonPaginations.add(getLastButton(p_ResponsePage.getTotalPages() -1, false, p_PaginationUrl, 5));
        }

        return buttonPaginations;
    }

    public static List<ButtonPagination> buildChangedPagination(ResponsePaginationDTO p_ResponsePage, String p_PaginationUrl, int p_Size) {
        List<ButtonPagination> buttonPaginations = new ArrayList<>();
        if (p_ResponsePage.isFirst()) {
            buttonPaginations.add(getStartFirstButton());
            buttonPaginations.add(getStartPreviousButton());
        }else {
            buttonPaginations.add(getChangedFirstButton(p_PaginationUrl, p_Size));
            buttonPaginations.add(getChangedPrevButton(p_ResponsePage.getNumber()-1, p_PaginationUrl, p_Size));
        }
        if (p_ResponsePage.getTotalPages()<5 && p_ResponsePage.getTotalPages() > 0) {
            for (int a=0; a<p_ResponsePage.getTotalPages(); a++) {
                buttonPaginations.add(getIndexedButton(a, p_ResponsePage.getNumber(), p_PaginationUrl, p_Size));
            }
        }else {
            if (p_ResponsePage.getNumber() < 3) {
                for (int a=0; a<5; a++) {
                    buttonPaginations.add(getIndexedButton(a, p_ResponsePage.getNumber(), p_PaginationUrl, p_Size));
                }
            }else {
                if (((p_ResponsePage.getNumber()+2) <= p_ResponsePage.getTotalPages()) && (p_ResponsePage.getNumber()-2 >0)) {
                    for (int a=p_ResponsePage.getNumber()-3; a<p_ResponsePage.getNumber()+2; a++) {
                        buttonPaginations.add(getIndexedButton(a, p_ResponsePage.getNumber(), p_PaginationUrl, p_Size));
                    }
                }else {
                    for (int a=p_ResponsePage.getNumber()-4; a<p_ResponsePage.getTotalPages(); a++) {
                        buttonPaginations.add(getIndexedButton(a, p_ResponsePage.getNumber(), p_PaginationUrl, p_Size));
                    }
                }
            }
        }
        if (p_ResponsePage.isLast()) {
            buttonPaginations.add(getNextButton(p_ResponsePage.getNumber()+1, true, p_PaginationUrl, p_Size));
            buttonPaginations.add(getLastButton(p_ResponsePage.getTotalPages(), true, p_PaginationUrl, p_Size));
        }else {
            buttonPaginations.add(getNextButton(p_ResponsePage.getNumber()+1, false, p_PaginationUrl, p_Size));
            buttonPaginations.add(getLastButton(p_ResponsePage.getTotalPages()-1, false, p_PaginationUrl, p_Size));
        }
        return buttonPaginations;
    }
}
