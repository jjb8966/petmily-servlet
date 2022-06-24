package abandoned_animal.service;

import abandoned_animal.model.AbandonedAnimal;

import java.util.List;

public class AbandonedAnimalPage {

    private int total;  //12
    private int currentPage;  //2
    private List<AbandonedAnimal> content;
    private int totalPages; //1+1=2
    private int startPage;  //1
    private int endPage; //2

    public AbandonedAnimalPage(int total, int pageNo, int size, List<AbandonedAnimal> content) {
        this.total = total;
        this.currentPage = currentPage;
        this.content = content;

        if (total == 0) {
            totalPages = 0;
            startPage = 0;
            endPage = 0;
        } else {
            totalPages = total / size;
            if (total % size > 0) {
                totalPages++;
            }
            int modVal = currentPage % 5;
            startPage = currentPage / 5 * 5 + 1;
            if (modVal == 0) startPage -= 5;

            endPage = startPage + 4;
            if (endPage > totalPages) endPage = totalPages;
        }
    }

    public int getTotal() {
        return total;
    }

    public boolean hasNoArticles() {
        return total == 0;
    }

    public boolean hasArticles() {
        return total > 0;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public List<AbandonedAnimal> getContent() {
        return content;
    }

    public int getStartPage() {
        return startPage;
    }

    public int getEndPage() {
        return endPage;
    }
}
