
(add-to-list 'auto-mode-alist '("\\.hy\\'" . clojure-mode))

(add-hook 'clojure-mode-hook
          '(lambda ()
             (interactive)
             (define-key clojure-mode-map (kbd "C-w")
	       (lambda () (interactive) (repl-eval-last-expression "brepl")))

             (define-key clojure-mode-map (kbd "C-q")
	       (lambda () (interactive) (repl-eval-last-expression "nrepl")))

             (define-key clojure-mode-map (kbd "C-b")
	       (lambda () (interactive) (repl-eval-last-expression "benrepl")))

             (define-key clojure-mode-map (kbd "C-j")
	       (lambda () (interactive) (repl-eval-last-expression "jerrepl")))

             (define-key clojure-mode-map (kbd "C-e")
	       (lambda () (interactive) (repl-eval-last-expression "hyrepl")))))

(defun repl-send-input (input buffer)
  "Send INPUT into the client buffer and leave it visible."
  (save-selected-window
    (setq start-buffer (buffer-name))
    (switch-to-buffer-other-frame buffer)
    (goto-char (point-max))
    (insert input)
    (comint-send-input)
    (switch-to-buffer-other-frame start-buffer)
))

(defun expression-preceding-point ()
  "Return the expression preceding point as a string."
  (buffer-substring-no-properties
   (save-excursion (backward-sexp) (point))
   (point)))

(defun repl-eval-last-expression ( buffer )
  "Send the expression preceding point to the client buffer."
  (interactive)
  (repl-send-input (expression-preceding-point) buffer))

