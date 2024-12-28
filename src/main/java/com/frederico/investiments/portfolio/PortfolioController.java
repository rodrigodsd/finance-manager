package com.frederico.investiments.portfolio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

    private static final Logger logger = LoggerFactory.getLogger(PortfolioController.class);

    private final ImportPortfolioService importPortfolioService;

    public PortfolioController(ImportPortfolioService importPortfolioService) {
        this.importPortfolioService = importPortfolioService;
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes,
                                        @RequestParam("portfolioName") String portfolioName) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long investorId = ((Jwt) authentication.getPrincipal()).getClaim("investorId");

        if (file.isEmpty()) {
            logger.error("Uploaded file is empty");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Log file details
        String fileName = file.getOriginalFilename();
        String fileType = file.getContentType();
        long fileSize = file.getSize();

        logger.info("Received file: Name={}, Type={}, Size={}", fileName, fileType, fileSize);

        // Validate file type by extension
        assert fileName != null;
        if (!fileName.endsWith(".xlsx") && !fileName.endsWith(".xls")) {
            logger.error("Unsupported file type: {}", fileType);
            return new ResponseEntity<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }

        importPortfolioService.importPortfolio(file, portfolioName, investorId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
