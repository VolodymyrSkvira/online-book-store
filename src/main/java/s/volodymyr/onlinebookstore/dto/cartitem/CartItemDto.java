package s.volodymyr.onlinebookstore.dto.cartitem;

public record CartItemDto(
        Long id,
        Long bookId,
        String bookTitle,
        Long quantity) {
}
